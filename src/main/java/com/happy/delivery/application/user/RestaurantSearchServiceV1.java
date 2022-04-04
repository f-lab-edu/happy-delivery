package com.happy.delivery.application.user;

import com.happy.delivery.application.common.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import com.happy.delivery.application.user.command.RestaurantSearchCommand;
import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import com.happy.delivery.domain.user.repository.RestaurantSearchRepository;
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
  private final UserAddressRepository userAddressRepository;

  /**
   * RestaurantSearchServiceV1 constructor.
   */
  @Autowired
  public RestaurantSearchServiceV1(RestaurantSearchRepository restaurantSearchRepository,
      UserAddressRepository userAddressRepository) {
    this.restaurantSearchRepository = restaurantSearchRepository;
    this.userAddressRepository = userAddressRepository;
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
