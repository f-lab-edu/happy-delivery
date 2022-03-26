package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.mybatis.RestaurantSearchMapper;
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
}
