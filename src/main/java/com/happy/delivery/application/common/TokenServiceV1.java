package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.TokenCommand;
import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TokenServiceV1.
 * token 저장을 위한 service.
 */
@Service
public class TokenServiceV1 implements TokenService {

  private final TokenRepository tokenRepository;

  @Autowired
  public TokenServiceV1(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public void saveToken(TokenCommand tokenCommand) {
    tokenRepository.save(new Token(
        tokenCommand.getToken(),
        tokenCommand.getId(),
        tokenCommand.getStatus()));
  }
}
