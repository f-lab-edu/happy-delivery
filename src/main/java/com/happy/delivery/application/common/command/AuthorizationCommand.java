package com.happy.delivery.application.common.command;

import com.happy.delivery.domain.enumeration.Authority;

/**
 * AuthorizationCommand.
 * 인가를 위한 command 값
 */
public class AuthorizationCommand {

  private Long memberId;
  private Authority authority;

  /**
   * AuthorizationCommand Constructor.
   */
  public AuthorizationCommand(Long memberId, Authority authority) {
    this.memberId = memberId;
    this.authority = authority;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Authority getAuthority() {
    return authority;
  }
}
