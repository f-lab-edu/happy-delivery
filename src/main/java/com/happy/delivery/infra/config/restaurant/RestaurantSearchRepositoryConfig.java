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

  @Autowired
  private JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository;

  @Autowired
  private JpaRestaurantRepository jpaRestaurantRepository;

  @Bean
  public RestaurantSearchRepository jpaRestaurantSearchRepositoryAdapter() {
    return new JpaRestaurantSearchRepositoryAdapter(jpaRestaurantCategoryRepository,
        jpaRestaurantRepository);
  }

  //  @Autowired
  //  private RestaurantSearchMapper restaurantSearchMapper;
  //
  //  @Bean
  //  public RestaurantSearchRepository restaurantSearchRepositoryAdapter() {
  //    return new RestaurantSearchRepositoryAdapter(restaurantSearchMapper);
  //  }

}
