package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import java.util.List;

/**
 * RestaurantSearchRepository.
 */
public interface UserRestaurantSearchRepository {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurantsByCategory(Long categoryId, String addressCode);
}
