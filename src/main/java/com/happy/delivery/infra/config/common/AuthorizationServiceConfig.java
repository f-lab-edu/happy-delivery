package com.happy.delivery.infra.config.common;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.application.common.TokenAuthorizationService;
import com.happy.delivery.domain.common.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AuthorizationServiceConfig.
 * 세션을 사용할 것인지, 토큰을 사용할 것인지 정함.
 */
@Configuration
public class AuthorizationServiceConfig {

  @Autowired
  private TokenRepository tokenRepository;

  @Bean
  public AuthorizationService tokenAuthorizationService() {
    return new TokenAuthorizationService(tokenRepository);
  }

  //  private final HttpSession httpSession;
  //
  //  public AuthorizationServiceConfig(HttpSession httpSession) {
  //    this.httpSession = httpSession;
  //  }
  //
  //  @Bean
  //  public AuthorizationService sessionAuthorizationService() {
  //    return new SessionAuthorizationService(httpSession);
  //  }

}
