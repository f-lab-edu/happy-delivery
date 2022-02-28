package com.happy.delivery.domain.exception.user;

/**
 * NoUserIdMatchedException.
 */
public class NoUserIdMatchedException extends RuntimeException {

  public NoUserIdMatchedException() {
  }

  public NoUserIdMatchedException(String message) {
    super(message);
  }
}
