package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.command.RestaurantSearchCommand;
import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
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
  public List<RestaurantResult> restaurantSearchByCategory(String category,
      RestaurantSearchCommand restaurantSearchCommand) {
    Restaurant restaurantLocation = restaurantSearchCommand.toRestaurant();
    List<Restaurant> listOfRestaurant =
        restaurantSearchRepository.getAllRestaurantsByCategory(category,
            restaurantLocation.getLongitude(), restaurantLocation.getLatitude());
    List<RestaurantResult> result = new ArrayList<>();
    for (Restaurant restaurant : listOfRestaurant) {
      result.add(RestaurantResult.fromRestaurant(restaurant));
    }
    return result;
  }


}
