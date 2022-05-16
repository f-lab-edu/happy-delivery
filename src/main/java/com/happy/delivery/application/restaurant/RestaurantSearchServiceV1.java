package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.command.RestaurantSearchCommand;
import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RestaurantSearchServiceV1.
 */
@Service
public class RestaurantSearchServiceV1 implements RestaurantSearchService {

  private final RestaurantSearchRepository restaurantSearchRepository;

  /**
   * RestaurantSearchServiceV1 constructor.
   */
  @Autowired
  public RestaurantSearchServiceV1(RestaurantSearchRepository restaurantSearchRepository) {
    this.restaurantSearchRepository = restaurantSearchRepository;
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
