package com.happy.delivery.domain.exception.user;

/**
 * EmailIsNotMatchException.
 */
public class EmailIsNotMatchException extends RuntimeException {

  public EmailIsNotMatchException() {
  }

  public EmailIsNotMatchException(String message) {
    super(message);
  }
}
