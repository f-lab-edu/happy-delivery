package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.common.result.AuthorizationResult;
import com.happy.delivery.domain.vo.SessionValue;
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
  public AuthorizationResult getCurrentUser() {
    SessionValue sessionValue = (SessionValue) httpSession.getAttribute(CURRENT_USER_SESSION_VALUE);
    // LoginCheckAspect 의 예외처리를 위해 만들어 둔 코드.
    if (sessionValue == null) {
      return null;
    }
    return AuthorizationResult.fromSessionValue(sessionValue);
  }

  @Override
  public void logout() {
    httpSession.invalidate();
  }
}
