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
    List<Restaurant> allOfRestaurants = restaurantSearchRepository.getAllRestaurants();
    // restaurantCacheRepository, RestaurantRedisTemplate 을 사용해서 Redis 에 allOfRestaurants 넣어줄 것.
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
    // 어떤 방식으로 거리를 계산해 restaurant 를 가져올 것인지 생각해봐야 함.
    // 지금은 카테고리별로 모든 식당을 가져오는 코드임.
    // restaurantSearchCommand 를 사용하지 않았음.
    List<Restaurant> listOfRestaurant =
        restaurantSearchRepository.getAllRestaurantsByCategory(category);
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
