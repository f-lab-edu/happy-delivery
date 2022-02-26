package com.happy.delivery.domain.exception.user;

/**
 * PasswordIsNotMatchException.
 */
public class PasswordIsNotMatchException extends RuntimeException {

  public PasswordIsNotMatchException() {
  }

  public PasswordIsNotMatchException(String message) {
    super(message);
  }
}
