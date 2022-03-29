package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.UserRestaurantSearchRepository;
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
  public List<Restaurant> getAllRestaurantsByCategory(String category, String addressCode) {
    return userRestaurantSearchMapper.getAllRestaurantsByCategory(category, addressCode);
  }
}
