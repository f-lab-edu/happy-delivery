package com.happy.delivery.infra.jpa.restaurant;

import com.happy.delivery.domain.restaurant.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRestaurantRepository.
 */
public interface JpaRestaurantRepository extends JpaRepository<Restaurant, Long> {

  List<Restaurant> findRestaurantsByCategory(String category);

  Restaurant findAllById(Long restaurantId);
}
