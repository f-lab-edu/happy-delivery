package com.happy.delivery.infra.config.restaurant;

import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import com.happy.delivery.infra.repository.restaurant.adapter.JpaRestaurantSearchRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RestaurantSearchRepositoryConfig.
 */
@Configuration
public class RestaurantSearchRepositoryConfig {

  private final JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository;

  @Autowired
  public RestaurantSearchRepositoryConfig(
      JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository) {
    this.jpaRestaurantCategoryRepository = jpaRestaurantCategoryRepository;
  }

  @Bean
  public RestaurantSearchRepository jpaRestaurantSearchRepositoryAdapter() {
    return new JpaRestaurantSearchRepositoryAdapter(jpaRestaurantCategoryRepository);
  }

}
