package com.happy.delivery.domain.exception.common;

/**
 * NotLoggedInException.
 */
public class NullTokenValueException extends RuntimeException {

  public NullTokenValueException() {
  }

  public NullTokenValueException(String message) {
    super(message);
  }
}
