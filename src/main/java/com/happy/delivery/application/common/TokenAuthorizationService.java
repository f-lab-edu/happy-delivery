package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.common.result.AuthorizationResult;
import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.repository.TokenRepository;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TokenAuthorizationService.
 * token 을 이용한 인가 처리 service.
 */
public class TokenAuthorizationService implements AuthorizationService {

  private final TokenRepository tokenRepository;

  @Autowired
  public TokenAuthorizationService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public String login(AuthorizationCommand authorizationCommand) {
    String token = UUID.randomUUID().toString();
    tokenRepository.save(new Token(
        token,
        authorizationCommand.getMemberId(),
        authorizationCommand.getAuthority()));
    return token;
  }

  @Override
  public AuthorizationResult getCurrentUser(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    Token tokenById = tokenRepository.findByToken(token);
    // LoginCheckAspect 의 예외처리를 위해 만들어 둔 코드.
    if (tokenById == null) {
      return null;
    }
    return AuthorizationResult.fromToken(tokenById);
  }

  @Override
  public void logout(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    tokenRepository.deleteByToken(token);
  }
}
