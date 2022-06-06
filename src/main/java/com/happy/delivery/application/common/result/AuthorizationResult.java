package com.happy.delivery.application.common.result;

import com.happy.delivery.domain.common.Token;
import com.happy.delivery.domain.common.vo.SessionValue;
import com.happy.delivery.domain.enumeration.Authority;

/**
 * AuthorizationResult.
 * 인가를 위한 result 값
 */
public class AuthorizationResult {

  private final Long memberId;
  private final Authority authority;

  /**
   * AuthorizationResult Constructor.
   */
  public AuthorizationResult(Long memberId, Authority authority) {
    this.memberId = memberId;
    this.authority = authority;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Authority getAuthority() {
    return authority;
  }

  /**
   * fromToken.
   * Token(domain) 을 AuthorizationResult(application) 형태로 변경하는 메서드.
   */
  public static AuthorizationResult fromToken(Token token) {
    return new AuthorizationResult(
        token.getMemberId(),
        token.getAuthority());
  }

  /**
   * fromSessionValue.
   * SessionValue(domain) 를 AuthorizationResult(application) 의 형태로 변경하는 메서드.
   */
  public static AuthorizationResult fromSessionValue(SessionValue sessionValue) {
    return new AuthorizationResult(
        sessionValue.getMemberId(),
        sessionValue.getAuthority());
  }
}
