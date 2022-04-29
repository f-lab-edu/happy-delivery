package com.happy.delivery.domain.common;

import javax.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * AuthorizationToken.
 */
@RedisHash
public class AuthorizationToken {

  @Id
  private final String token;
  private final Long id;
  private final String authority;

  /**
   * AuthorizationToken constructor.
   */
  public AuthorizationToken(String token, Long id, String authority) {
    this.token = token;
    this.id = id;
    this.authority = authority;
  }

  public String getToken() {
    return token;
  }

  public Long getId() {
    return id;
  }

  public String getAuthority() {
    return authority;
  }
}
