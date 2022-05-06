package com.happy.delivery.domain.exception.common;

/**
 * NotLoggedInException.
 */
public class NotLoggedInException extends RuntimeException {

  public NotLoggedInException() {
  }

  public NotLoggedInException(String message) {
    super(message);
  }
}
