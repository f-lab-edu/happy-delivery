package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.common.result.AuthorizationResult;
import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.repository.TokenRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
  public void login(AuthorizationCommand authorizationCommand) {
    String token = getTokenFromRequestHeader();
    tokenRepository.save(new Token(
        token,
        authorizationCommand.getMemberId(),
        authorizationCommand.getAuthority()));
  }

  @Override
  public AuthorizationResult getCurrentUser() {
    String token = getTokenFromRequestHeader();
    Token tokenById = tokenRepository.findByToken(token);
    // LoginCheckAspect 의 예외처리를 위해 만들어 둔 코드.
    if (tokenById == null) {
      return null;
    }
    return AuthorizationResult.fromToken(tokenById);
  }

  @Override
  public void logout() {
    String token = getTokenFromRequestHeader();
    tokenRepository.deleteByToken(token);
  }

  private String getTokenFromRequestHeader() {
    /*
            RequestContextHolder : Request 에 대한 정보를 가져오고자 할 때 사용하는 유틸성 클래스.
                                   T현재 Thread 에 RequestAttributes 를 바인딩 해뒀다가
                                   요청하면 이 값을 반환한다.

            currentRequestAttributes() : RequestAttributes 가 없으면 예외 발생.

            getRequestAttributes() : RequestAttributes 가 없으면 null 을 반환.
     */
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    return request.getHeader("Authorization");
  }
}
