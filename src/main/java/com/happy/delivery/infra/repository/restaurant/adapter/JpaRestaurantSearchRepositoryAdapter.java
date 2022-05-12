package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JpaRestaurantSearchRepositoryAdapter.
 */
public class JpaRestaurantSearchRepositoryAdapter implements RestaurantSearchRepository {

  private final JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository;

  @Autowired
  public JpaRestaurantSearchRepositoryAdapter(
      JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository) {
    this.jpaRestaurantCategoryRepository = jpaRestaurantCategoryRepository;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return jpaRestaurantCategoryRepository.findAll();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(String category) {
    return null;
  }
}
