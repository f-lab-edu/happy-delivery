package com.happy.delivery.infra.redis.restaurant;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.repository.RestaurantCacheRepository;
import org.springframework.stereotype.Repository;

/**
 * RestaurantRedisTemplate.
 * 레스토랑의 longitude, latitude, id를 Redis 에 저장하기 위해서 만든 RedisTemplate.
 */
@Repository
public class RestaurantRedisTemplate implements RestaurantCacheRepository {

  @Override
  public void save(Restaurant restaurant) {}

  @Override
  public Restaurant findById(String id) {
    return null;
  }

  @Override
  public void deleteById(String id) {}
}
