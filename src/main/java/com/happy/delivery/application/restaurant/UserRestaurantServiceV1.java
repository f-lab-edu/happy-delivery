package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.UserRestaurantSearchRepository;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
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
  public List<RestaurantResult> restaurantSearchByCategory(Long category, Long addressId) {
    String addressCode = userAddressRepository.findById(addressId).getAddressCode();
    List<Restaurant> listOfRestaurant =
        userRestaurantSearchRepository.getAllRestaurantsByCategory(category, addressCode);
    List<RestaurantResult> result = new ArrayList<>();
    for (Restaurant restaurant : listOfRestaurant) {
      result.add(RestaurantResult.fromRestaurant(restaurant));
    }
    return result;
  }


}
