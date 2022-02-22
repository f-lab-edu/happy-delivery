package com.happy.delivery.domain.exception.user;

public class NoUserIdMatchedException extends RuntimeException {

  public NoUserIdMatchedException() {
  }

  public NoUserIdMatchedException(String message) {
    super(message);
  }
}
