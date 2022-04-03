package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import com.happy.delivery.domain.user.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.mybatis.user.RestaurantSearchMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RestaurantSearchAdapter.
 */
@Repository
public class RestaurantSearchAdapter implements RestaurantSearchRepository {

  private final RestaurantSearchMapper restaurantSearchMapper;

  public RestaurantSearchAdapter(RestaurantSearchMapper restaurantSearchMapper) {
    this.restaurantSearchMapper = restaurantSearchMapper;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return restaurantSearchMapper.getAllCategories();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(String category,
      Double longitude, Double latitude) {
    return restaurantSearchMapper.getAllRestaurantsByCategory(category, longitude, latitude);
  }
}
