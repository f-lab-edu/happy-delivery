package com.happy.delivery.infra.config.redis;

import com.happy.delivery.domain.common.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfig
 * localhost:6379 는 기본값이기 때문에 만약 Redis 를 localhost:6379 로 띄웠다면 따로 설정하지 않아도 연결이 됩니다.
 * 하지만 일반적으로 운영 서버에서는 별도의 Host 를 사용하기 때문에,
 * 값을 이렇게 별도의 값을 세팅하고 Configuration 에서 Bean 에 등록해줍니다.
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  /**
        Lettuce : Multi-Thread 에서 Thread-Safe 한 Redis 클라이언트로 netty 에 의해 관리된다.
                 Sentinel, Cluster, Redis data model 같은 고급 기능들을 지원하며
                 비동기 방식으로 요청하기에 TPS/CPU/Connection 개수와 응답속도 등 전 분야에서 Jedis 보다 뛰어나다.
                 스프링 부트의 기본 의존성은 현재 Lettuce 로 되어있다.

        Jedis  : Multi-Thread 에서 Thread-unsafe 하며 Connection pool 을 이용해 멀티쓰레드 환경을 구성한다.
                 Jedis 인스턴스와 연결할 때마다 Connection pool 을 불러오고 스레드 갯수가
                 늘어난다면 시간이 상당히 소요될 수 있다.
  */
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(host, port);
  }

  /**
          RedisTemplate: Redis data access code 를 간소화 하기 위해 제공되는 클래스이다.
                         주어진 객체들을 자동으로 직렬화/역직렬화 하며 binary 데이터를 Redis 에 저장한다.
                         기본설정은 JdkSerializationRedisSerializer 이다.

          StringRedisSerializer: binary 데이터로 저장되기 때문에 이를 String 으로 변환시켜주며(반대로도 가능)
                                 UTF-8 인코딩 방식을 사용한다.

          GenericJackson2JsonRedisSerializer: 객체를 json 타입으로 직렬화/역직렬화를 수행한다.
          => 기본 StringRedisSerializer 를 사용하면 serialized 오류가 발생한다.
   */
  @Bean
  public RedisTemplate<String, Token> redisTemplate() {
    RedisTemplate<String, Token> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
  }

  /**
          StringRedisTemplate : 주어진 객체들을 자동으로 직렬화/역직렬화 하며 String 데이터를 Redis 에 저장한다.
   */
  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
    stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
    stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
    return stringRedisTemplate;
  }
}
