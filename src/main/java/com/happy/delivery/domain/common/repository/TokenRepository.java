package com.happy.delivery.domain.common.repository;

import com.happy.delivery.domain.common.Token;

/**
 * TokenRepository.
 * 토큰 저장소를 위한 인터페이스.
 */
public interface TokenRepository {

  void save(Token token);

  Token findByToken(String token);

  void deleteByToken(String token);
}
