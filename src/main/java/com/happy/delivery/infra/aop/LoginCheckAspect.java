package com.happy.delivery.infra.aop;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.domain.exception.common.NotLoggedInException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LoginCheckAspect.
 */
@Aspect
@Component
public class LoginCheckAspect {

  private final AuthorizationService authorizationService;

  @Autowired
  public LoginCheckAspect(AuthorizationService authorizationService) {
    this.authorizationService = authorizationService;
  }

  /**
   * loginCheck advise.
   */
  @Before("@annotation(com.happy.delivery.infra.annotation.UserLoginCheck)")
  public void userLoginCheck() {
    if (authorizationService.getCurrentUser() == null) {
      throw new NotLoggedInException("로그인이 필요한 서비스입니다.");
    }
  }
}
