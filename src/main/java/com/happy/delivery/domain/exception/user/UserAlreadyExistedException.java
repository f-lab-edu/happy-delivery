package com.happy.delivery.domain.exception.user;

/**
 * UserAlreadyExistedException.
 */
public class UserAlreadyExistedException extends RuntimeException {

  public UserAlreadyExistedException() {
  }

  public UserAlreadyExistedException(String message) {
    super(message);
  }
}
