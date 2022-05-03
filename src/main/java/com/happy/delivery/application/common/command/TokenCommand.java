package com.happy.delivery.application.common.command;

import com.happy.delivery.infra.enumeration.Status;

/**
 * TokenCommand.
 * token 을 위한 command 값
 */
public class TokenCommand {

  private String token;
  private Long id;
  private Status status;

  /**
   * TokenCommand Constructor.
   */
  public TokenCommand(String token, Long id, Status status) {
    this.token = token;
    this.id = id;
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public Long getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }


}
