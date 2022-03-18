package com.happy.delivery.infra.aop;

import com.happy.delivery.domain.exception.user.NoUserIdException;
import com.happy.delivery.infra.util.SessionUtil;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * LoginCheckAspect.
 */
@Aspect
@Component
public class LoginCheckAspect {

  /**
   * loginCheck advise.
   */
  @Before("@annotation(com.happy.delivery.infra.annotation.UserLoginCheck)")
  public void userLoginCheck() throws NoUserIdException {
    HttpSession httpSession =
        ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
            .getRequest()
            .getSession();
    if (SessionUtil.getLoginId(httpSession) == null) {
      throw new NoUserIdException("로그인이 필요한 서비스입니다.");
    }
  }
}
