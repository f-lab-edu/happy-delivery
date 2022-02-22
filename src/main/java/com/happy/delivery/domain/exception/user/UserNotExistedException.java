package com.happy.delivery.domain.exception.user;

public class UserNotExistedException extends RuntimeException {

  public UserNotExistedException() {
  }

  public UserNotExistedException(String message) {
    super(message);
  }
}
