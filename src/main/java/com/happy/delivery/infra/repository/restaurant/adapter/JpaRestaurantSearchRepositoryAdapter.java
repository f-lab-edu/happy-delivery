package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JpaRestaurantSearchRepositoryAdapter.
 */
public class JpaRestaurantSearchRepositoryAdapter implements RestaurantSearchRepository {

  private final JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository;
  private final JpaRestaurantRepository jpaRestaurantRepository;

  @Autowired
  public JpaRestaurantSearchRepositoryAdapter(
      JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository,
      JpaRestaurantRepository jpaRestaurantRepository) {
    this.jpaRestaurantCategoryRepository = jpaRestaurantCategoryRepository;
    this.jpaRestaurantRepository = jpaRestaurantRepository;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return jpaRestaurantCategoryRepository.findAll();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(String category) {
    return jpaRestaurantRepository.findRestaurantsByCategory(category);
  }

  @Override
  public Restaurant getRestaurantInfoAndAllMenus(Long restaurantId) {
    return jpaRestaurantRepository.findAllById(restaurantId);
  }
}
