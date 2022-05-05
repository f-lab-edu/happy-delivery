package com.happy.delivery.infra.redis.common;

import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.repository.TokenRepository;
import com.happy.delivery.domain.exception.common.NullTokenValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * TokenRedisTemplate.
 * Token 저장 용도로 Redis 를 사용하기 위해 만든 클래스.
 */
@Repository
public class TokenRedisTemplate implements TokenRepository {

  private final RedisTemplate<String, Token> redisTemplate;

  @Autowired
  public TokenRedisTemplate(RedisTemplate<String, Token> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void save(Token token) {
    redisTemplate.opsForValue().set(token.getToken(), token);
  }

  @Override
  public Token findByToken(String token) {
    if (token == null) {
      throw new NullTokenValueException("토큰값이 null 입니다.");
    }
    Object tokenObj = redisTemplate.opsForValue().get(token);
    return (Token) tokenObj;
  }

  @Override
  public void deleteByToken(String token) {
    redisTemplate.delete(token);
  }
}
