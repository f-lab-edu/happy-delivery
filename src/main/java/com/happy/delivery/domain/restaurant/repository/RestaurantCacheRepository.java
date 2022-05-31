package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Restaurant;

/**
 * RestaurantCacheRepository.
 * Restaurant 정보를 Cache 로 저장하기 위해 만든 Repository.
 */
public interface RestaurantCacheRepository {

  void save(Restaurant restaurant);

  Restaurant findById(String id);

  void deleteById(String id);
}
