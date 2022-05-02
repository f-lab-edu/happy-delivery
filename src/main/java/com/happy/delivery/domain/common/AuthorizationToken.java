package com.happy.delivery.domain.common;

import com.happy.delivery.infra.enumeration.Status;
import java.io.Serializable;

/**
 * AuthorizationToken.
 */
public class AuthorizationToken implements Serializable {

  private String token;
  private Long id;
  private Status status;

  /**
   * AuthorizationToken default constructor.
   */
  public AuthorizationToken() {
  }

  /**
   * AuthorizationToken constructor.
   */
  public AuthorizationToken(String token, Long id, Status status) {
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

  public Status getAuthority() {
    return status;
  }
}
