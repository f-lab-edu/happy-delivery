package com.happy.delivery.domain.exception.user;

/**
 * NotAuthorizedException.
 */
public class NotAuthorizedException extends RuntimeException {

  public NotAuthorizedException() {
  }

  public NotAuthorizedException(String message) {
    super(message);
  }
}
