package com.happy.delivery.infra.config.redis;

import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisCachingConfig.
 * Redis Caching 을 사용하기 위해서, Caching 을 활성화 하고 CacheManager 를 등록한다.
 */
@Configuration
@EnableCaching
public class RedisCachingConfig {

  /*

       Caching               : 애플리케이션 전반에 사용할 수 있다.
                               원본 데이터에 접근하는 시간이 오래 걸리는 때,
                               부담을 줄이고 성능을 높이기 위해서 사용하는 기술이다.

       Caching 작동 방식       : (1) 데이터 요청
                               (2) 캐시에 있는지 확인
                               (3) 있다면 캐시에서 가져오고 없다면 실제 DB 에서 데이터를 가져옴
                               (4) DB 에서 데이터를 가지고 왔다면 해당 값을 캐시에 저장함

       Caching 활용도 높이는 방법 : 자주 참조하지만, 수정이 잘 발생하지 않는 데이터로 구성해야 활용도가 높아진다.

       @EnableCaching  : 캐시 기능을 활성화한다.
                         Spring 에서 annotation 기반의 캐시 기능을 사용하는 경우 선언한다.

        CacheManager   : 저장된 캐시를 관리한다.
                         CacheManager 는 종류가 다양한데, 자신의 필요에 맞게 선택하면 된다.
                         여기서는 별도의 Redis Cache Configuration 설정을 통해 RedisCacheManager 를 사용한다.
                         RedisCacheConfiguration 는 TTL, disableCachingNullValues, key&value 직렬화 등
                         캐싱 정책을 설정할 수 있다.

   */
  /**
   * restaurantCacheManager.
   * 저장된 캐시를 관리하는 CacheManager.
   * 아직 적용한 곳은 없음!!
   */
  @Bean
  public CacheManager restaurantCacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
            new GenericJackson2JsonRedisSerializer()))
        .disableCachingNullValues()
        .entryTtl(Duration.ofHours(5L));
    return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
        .cacheDefaults(redisCacheConfiguration).build();
  }

}
