package com.happy.delivery.infra.jpa.restaurant;

import com.happy.delivery.domain.restaurant.RestaurantCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRestaurantCategoryRepository.
 */
public interface JpaRestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {

  List<RestaurantCategory> findAll();
}
