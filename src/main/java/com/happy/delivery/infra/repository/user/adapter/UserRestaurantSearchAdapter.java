package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import com.happy.delivery.domain.user.repository.UserRestaurantSearchRepository;
import com.happy.delivery.infra.mybatis.UserRestaurantSearchMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RestaurantSearchAdapter.
 */
@Repository
public class UserRestaurantSearchAdapter implements UserRestaurantSearchRepository {

  private final UserRestaurantSearchMapper userRestaurantSearchMapper;

  public UserRestaurantSearchAdapter(UserRestaurantSearchMapper userRestaurantSearchMapper) {
    this.userRestaurantSearchMapper = userRestaurantSearchMapper;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return userRestaurantSearchMapper.getAllCategories();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(String category,
      Double longitude, Double latitude) {
    return userRestaurantSearchMapper.getAllRestaurantsByCategory(category, longitude, latitude);
  }
}
