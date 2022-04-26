package com.happy.delivery.infra.config.restaurant;

import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantRepository;
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
  private final JpaRestaurantRepository jpaRestaurantRepository;

  @Autowired
  public RestaurantSearchRepositoryConfig(
      JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository,
      JpaRestaurantRepository jpaRestaurantRepository) {
    this.jpaRestaurantCategoryRepository = jpaRestaurantCategoryRepository;
    this.jpaRestaurantRepository = jpaRestaurantRepository;
  }

  @Bean
  public RestaurantSearchRepository jpaRestaurantSearchRepositoryAdapter() {
    return new JpaRestaurantSearchRepositoryAdapter(jpaRestaurantCategoryRepository,
        jpaRestaurantRepository);
  }

}
