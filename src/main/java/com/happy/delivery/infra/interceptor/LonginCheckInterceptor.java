package com.happy.delivery.infra.interceptor;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.application.common.result.AuthorizationResult;
import com.happy.delivery.domain.enumeration.Authority;
import com.happy.delivery.domain.exception.common.MisMatchedAuthorityException;
import com.happy.delivery.domain.exception.common.NotLoggedInException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * LonginCheckInterceptor.
 * 로그인 체크를 위한 인터셉터.
 */
public class LonginCheckInterceptor implements HandlerInterceptor {

  /*
          공통 프로세스 처리 방식

          Filter : <Servlet 단위 작동>
                   스프링 컨텍스트 외부에 존재하기 때문에 스프링과 무관하다.
                   DispatcherServlet 이전에 실행한다.
                   필터가 동작하도록 지정된 자원의 앞단에서 요청내용을 변경하거나, 여러가지 체크를 수행할 수 있다.

          Interceptor : <Servlet 단위 작동>
                        DispatcherServlet 이 Controller(Handler) 를 호출하기 전/후로 끼어든다.
                        스프링 컨텍스트 내부에서 Controller(Handler)에 관한 요청과 응답에 대해 처리한다.
                        스프링의 모든 빈 객체에 접근할 수 있다.
                        로그인 체크, 권한 체크, 로그확인 등의 업무처리를 한다.

          AOP : <Proxy 단위 작동>
                Interceptor 나 Filter 와 달리 메소드 전후의 지점에 자유롭게 설정이 가능하다.
                Interceptor 와 Filter 는 주소로 대상을 구분해서 걸러내야하는 반면,
                 AOP 는 주소, 파라미터, 애노테이션 등 다양한 방법으로 대상을 지정할 수 있다.
   */

  private final AuthorizationService authorizationService;

  @Autowired
  public LonginCheckInterceptor(AuthorizationService authorizationService) {
    this.authorizationService = authorizationService;
  }

  /**
   * preHandle().
   * Controller 실행 전에 실행되는 메소드.
   * return 값이 True 면 컨트톨러로 진입하고, False 일 경우 진입하지 않음.
   * Object handler 에는 진입하려는 Controller 의 class 객체가 있다.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    AuthorizationResult currentUser = authorizationService.getCurrentUser(request);

    if (currentUser == null) {
      throw new NotLoggedInException("로그인이 필요한 서비스입니다.");
    }

    if (currentUser.getAuthority() != Authority.USER) {
      throw new MisMatchedAuthorityException("사용자 권한이 없습니다.");
    }

    return true;
  }
}
