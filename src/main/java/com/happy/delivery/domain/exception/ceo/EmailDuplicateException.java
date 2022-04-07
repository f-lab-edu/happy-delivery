package com.happy.delivery.domain.exception.ceo;

/**
 * EmailDuplicateException.
 */
public class EmailDuplicateException extends RuntimeException {

  public EmailDuplicateException() {
  }

  public EmailDuplicateException(String message) {
    super(message);
  }
}
