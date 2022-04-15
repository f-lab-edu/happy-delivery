package com.happy.delivery.domain.exception.user;

/**
 * NoUserIdException.
 */
public class NoUserException extends RuntimeException {

  public NoUserException() {
  }

  public NoUserException(String message) {
    super(message);
  }
}
