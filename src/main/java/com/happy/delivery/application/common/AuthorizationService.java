package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.common.result.AuthorizationResult;
import javax.servlet.http.HttpServletRequest;

/**
 * AuthorizationService.
 *  인가 관련 코드는 여러 Controller 사용할 수 있기 때문에 Service 로 구현함.
 *  지금은 토큰 방식을 사용하였으나, 여러 형태로 인가가 가능하기 떄문에
 *  인터페이스를 사용해 컨트롤러가 서비스를 간접적으로 의존하게 만듦.
 *  컨트롤러는 어떤방식(세션,토큰 등)으로 로그인을 구현하였는지 알 필요가 없으며 interface 메소드만 사용하면 됨.
 * */
public interface AuthorizationService {

  String login(AuthorizationCommand authorizationCommand);

  AuthorizationResult getCurrentUser(HttpServletRequest request);

  void logout(HttpServletRequest request);
}
