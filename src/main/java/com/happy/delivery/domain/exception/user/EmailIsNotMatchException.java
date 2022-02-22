package com.happy.delivery.domain.exception.user;

public class EmailIsNotMatchException extends RuntimeException {

  public EmailIsNotMatchException() {
  }

  public EmailIsNotMatchException(String message) {
    super(message);
  }
}
