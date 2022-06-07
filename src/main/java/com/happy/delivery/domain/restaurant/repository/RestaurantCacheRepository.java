package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.vo.RestaurantSearchValue;
import java.util.List;

/**
 * RestaurantCacheRepository.
 * Restaurant 정보를 Cache 로 저장하기 위해 만든 Repository.
 */
public interface RestaurantCacheRepository {

  void save(List<Restaurant> restaurant);

  List<Long> searchNearbyRestaurants(RestaurantSearchValue restaurantSearchValue);
}
