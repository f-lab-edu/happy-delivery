package com.happy.delivery.domain.exception.user;

/**
 * UnexpectedCategoryException.
 */
public class UnexpectedCategoryException extends RuntimeException {

  public UnexpectedCategoryException() {
  }

  public UnexpectedCategoryException(String message) {
    super(message);
  }
}
