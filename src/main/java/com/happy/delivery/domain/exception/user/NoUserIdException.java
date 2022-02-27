package com.happy.delivery.domain.exception.user;

/**
 * NoUserIdException.
 */
public class NoUserIdException extends RuntimeException {

  public NoUserIdException() {
  }

  public NoUserIdException(String message) {
    super(message);
  }
}
