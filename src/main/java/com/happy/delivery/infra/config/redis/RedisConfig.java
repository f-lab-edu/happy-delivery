package com.happy.delivery.infra.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * RedisConfig
 * localhost:6379 는 기본값이기 때문에 만약 Redis 를 localhost:6379 로 띄웠다면 따로 설정하지 않아도 연결이 됩니다.
 * 하지만 일반적으로 운영 서버에서는 별도의 Host 를 사용하기 때문에,
 * 값을 이렇게 별도의 값을 세팅하고 Configuration 에서 Bean 에 등록해줍니다.
 */
@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(host, port);
  }
}
