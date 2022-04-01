package com.happy.delivery.application.user;

import com.happy.delivery.application.common.restaurant.command.RestaurantLocationCommand;
import com.happy.delivery.application.common.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import com.happy.delivery.domain.common.restaurant.RestaurantLocation;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.domain.user.repository.UserRestaurantSearchRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRestaurantServiceV1.
 */
@Service
public class UserRestaurantServiceV1 implements UserRestaurantService {

  private final UserRestaurantSearchRepository userRestaurantSearchRepository;
  private final UserAddressRepository userAddressRepository;

  /**
   * RestaurantServiceV1 constructor.
   */
  @Autowired
  public UserRestaurantServiceV1(UserRestaurantSearchRepository userRestaurantSearchRepository,
      UserAddressRepository userAddressRepository) {
    this.userRestaurantSearchRepository = userRestaurantSearchRepository;
    this.userAddressRepository = userAddressRepository;
  }

  @Override
  @Transactional
  public List<RestaurantCategoryResult> getCategories() {
    List<RestaurantCategory> categories = userRestaurantSearchRepository.getAllCategories();
    List<RestaurantCategoryResult> result = new ArrayList<>();
    for (RestaurantCategory category : categories) {
      result.add(RestaurantCategoryResult.fromRestaurantCategory(category));
    }
    return result;
  }

  @Override
  @Transactional
  public List<RestaurantResult> restaurantSearchByCategory(String category,
      RestaurantLocationCommand locationCommand) {
    RestaurantLocation restaurantLocation = locationCommand.toRestaurantLocation();
    List<Restaurant> listOfRestaurant =
        userRestaurantSearchRepository.getAllRestaurantsByCategory(category,
            restaurantLocation.getLongitude(), restaurantLocation.getLatitude());
    List<RestaurantResult> result = new ArrayList<>();
    for (Restaurant restaurant : listOfRestaurant) {
      result.add(RestaurantResult.fromRestaurant(restaurant));
    }
    return result;
  }


}
