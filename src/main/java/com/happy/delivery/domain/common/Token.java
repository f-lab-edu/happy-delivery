package com.happy.delivery.domain.common;

import com.happy.delivery.infra.enumeration.Status;
import java.io.Serializable;

/**
 * AuthorizationToken.
 */
public class Token implements Serializable {

  private String token;
  private Long id;
  private Status status;

  /**
   * AuthorizationToken default constructor.
   */
  public Token() {
  }

  /**
   * AuthorizationToken constructor.
   */
  public Token(String token, Long id, Status status) {
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
