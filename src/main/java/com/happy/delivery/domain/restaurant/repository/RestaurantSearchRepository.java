package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import java.util.List;

/**
 * RestaurantSearchRepository.
 */
public interface RestaurantSearchRepository {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurants();

  List<Restaurant> getAllRestaurantsByCategory(List<Long> restaurantIdList, String category);

  Restaurant getRestaurantInfoAndAllMenus(Long restaurantId);
}
