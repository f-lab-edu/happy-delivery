package com.happy.delivery.infra.aop;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.domain.exception.common.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * LoginCheckAspect.
 * 처음엔 AOP 를 사용해서 로그인 체크를 했지만,
 * interceptor 가 더 알맞은 방식이라고 판단해 더이상 사용하지 않음.
 */
@Aspect
//Component
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
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    if (authorizationService.getCurrentUser(request) == null) {
      throw new NotLoggedInException("로그인이 필요한 서비스입니다.");
    }
  }
}
