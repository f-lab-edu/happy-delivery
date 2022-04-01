package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import java.util.List;

/**
 * RestaurantSearchRepository.
 */
public interface UserRestaurantSearchRepository {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurantsByCategory(String categoryId,
      Double longitude, Double latitude);
}
