package com.happy.delivery.domain.exception.user;

/**
 * UserNotExistedException.
 */
public class UserNotExistedException extends RuntimeException {

  public UserNotExistedException() {
  }

  public UserNotExistedException(String message) {
    super(message);
  }
}
