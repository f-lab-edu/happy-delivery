package com.happy.delivery.infra.redis.common;

import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * TokenRedisTemplate.
 * Token 저장 용도로 Redis 를 사용하기 위해 만든 클래스.
 */
@Repository
public class TokenRedisTemplate implements TokenRepository {

  private final ValueOperations<String, Object> valueOperations;

  @Autowired
  public TokenRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    valueOperations = redisTemplate.opsForValue();
  }

  @Override
  public void save(Token token) {
    valueOperations.set(token.getToken(), token);
  }

  @Override
  public Token findById(String token) {
    Object tokenObj = valueOperations.get(token);
    return (Token) tokenObj;
  }
}
