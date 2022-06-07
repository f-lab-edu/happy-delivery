package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.command.RestaurantSearchCommand;
import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantCacheRepository;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RestaurantSearchServiceV1.
 */
@Service
public class RestaurantSearchServiceV1 implements RestaurantSearchService {

  private final RestaurantSearchRepository restaurantSearchRepository;
  private final RestaurantCacheRepository restaurantCacheRepository;

  /**
   * RestaurantSearchServiceV1 constructor.
   */
  @Autowired
  public RestaurantSearchServiceV1(RestaurantSearchRepository restaurantSearchRepository,
      RestaurantCacheRepository restaurantCacheRepository) {
    this.restaurantSearchRepository = restaurantSearchRepository;
    this.restaurantCacheRepository = restaurantCacheRepository;
  }

  /*
       PostConstruct 어노테이션  : 특정 클래스의 메서드에 사용하는 어노테이션으로,
                                해당 클래스 생성과 의존성 주입이 끝난 직후에 딱 한 번만 실행한다.
                                Bean 이 생성된 직후, 무조건 실행해야 하는 초기화 메서드를 위해서 사용한다.

       PostConstruct 사용 이유  : 단순하게 Bean 의 생성자에서 초기화 로직을 실행하면 안되나 생각할 수 있다.
                                그러나, 객체가 생성되는 시점엔 아직 주입되는 Bean 들이 초기화가 되지 않은 상태이므로
                                주입된 Bean 을 사용하는 경우 NPE 가 발생한다.
  */
  @PostConstruct
  public void init() {
    List<Restaurant> restaurants = restaurantSearchRepository.getAllRestaurants();
    restaurantCacheRepository.save(restaurants);
  }

  @Override
  @Transactional
  public List<RestaurantCategoryResult> getCategories() {
    List<RestaurantCategory> categories = restaurantSearchRepository.getAllCategories();
    List<RestaurantCategoryResult> result = new ArrayList<>();
    for (RestaurantCategory category : categories) {
      result.add(RestaurantCategoryResult.fromRestaurantCategory(category));
    }
    return result;
  }

  @Override
  @Transactional
  public List<RestaurantResult> getRestaurantsByCategoryAndPoint(String category,
      RestaurantSearchCommand restaurantSearchCommand) {
    // Redis 를 사용해서 근처 restaurants 가져오기.
    List<Long> nearbyRestaurantIdList = restaurantCacheRepository
        .searchNearbyRestaurants(restaurantSearchCommand.toRestaurantSearchValue());

    // 카테고리와 레스토랑 아이디를 이용해서 MySQL 에서 값 가져오기.
    List<Restaurant> listOfRestaurant =
        restaurantSearchRepository.getAllRestaurantsByCategory(nearbyRestaurantIdList, category);
    List<RestaurantResult> result = new ArrayList<>();
    for (Restaurant restaurant : listOfRestaurant) {
      result.add(RestaurantResult.fromRestaurantForList(restaurant));
    }
    return result;
  }

  @Override
  @Transactional
  public RestaurantResult getRestaurantInfoAndAllMenus(Long restaurantId) {
    Restaurant restaurant = restaurantSearchRepository.getRestaurantInfoAndAllMenus(restaurantId);
    return RestaurantResult.fromRestaurant(restaurant);
  }
}
