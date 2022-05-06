package com.happy.delivery.infra.aop;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.domain.exception.common.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    // 임시로 만들어 둔 request
    // AOP 자체를 filter 나 interceptor 로 변경할 예정.
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    if (authorizationService.getCurrentUser(request) == null) {
      throw new NotLoggedInException("로그인이 필요한 서비스입니다.");
    }
  }
}
