package com.happy.delivery.domain.common;

import com.happy.delivery.domain.enumeration.Authority;
import java.io.Serializable;

/**
 * AuthorizationToken.
 */
public class Token implements Serializable {

  private String token;
  private Long memberId;
  private Authority authority;

  /**
   * AuthorizationToken default constructor.
   */
  public Token() {
  }

  /**
   * AuthorizationToken constructor.
   */
  public Token(String token, Long memberId, Authority authority) {
    this.token = token;
    this.memberId = memberId;
    this.authority = authority;
  }

  public String getToken() {
    return token;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Authority getAuthority() {
    return authority;
  }
}
