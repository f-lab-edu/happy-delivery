package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.mybatis.restaurant.RestaurantSearchMapper;
import java.util.List;

/**
 * RestaurantSearchAdapter.
 */
public class RestaurantSearchRepositoryAdapter implements RestaurantSearchRepository {

  private final RestaurantSearchMapper restaurantSearchMapper;

  public RestaurantSearchRepositoryAdapter(RestaurantSearchMapper restaurantSearchMapper) {
    this.restaurantSearchMapper = restaurantSearchMapper;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return restaurantSearchMapper.getAllCategories();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(String category) {
    return restaurantSearchMapper.getAllRestaurantsByCategory(category);
  }
}
