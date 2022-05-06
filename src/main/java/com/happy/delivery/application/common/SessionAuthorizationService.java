package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.common.result.AuthorizationResult;
import com.happy.delivery.domain.vo.SessionValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SessionAuthorizationService.
 * 세션을 통해 인가 처리 service.
 */
public class SessionAuthorizationService implements AuthorizationService {

  private static final String CURRENT_USER_SESSION_VALUE = "CURRENT_USER_SESSION_VALUE";
  private final HttpSession httpSession;

  @Autowired
  public SessionAuthorizationService(HttpSession httpSession) {
    this.httpSession = httpSession;
  }

  @Override
  public String login(AuthorizationCommand authorizationCommand) {
    SessionValue sessionValue = new SessionValue(
        authorizationCommand.getMemberId(),
        authorizationCommand.getAuthority()
    );
    httpSession.setAttribute(CURRENT_USER_SESSION_VALUE, sessionValue);
    return null;
  }

  @Override
  public AuthorizationResult getCurrentUser(HttpServletRequest request) {
    SessionValue sessionValue =
        (SessionValue) request.getSession().getAttribute(CURRENT_USER_SESSION_VALUE);
    // LoginCheckAspect 의 예외처리를 위해 만들어 둔 코드.
    if (sessionValue == null) {
      return null;
    }
    return AuthorizationResult.fromSessionValue(sessionValue);
  }

  @Override
  public void logout(HttpServletRequest request) {
    /*

            HttpSession : 클라이언트마다 개별적으로 생성되어 일정 시간 동안 유지된다.
                          서버에서 생성하여 클라이언트에게 보냄.
                          클라이언트는 세션 ID를 쿠키로 저장하여 request 를 보낼 때마다 매번 함께 보냄.

            HttpServletRequest : 클라이언트의 서비스 요청 단위로 유지된다.

     */
    request.getSession().invalidate();
  }
}
