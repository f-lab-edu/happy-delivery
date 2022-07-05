package com.happy.delivery.domain.exception.common;

/**
 * MisMatchedAuthorityException.
 * 권한이 일치하지 않을 때 사용하는 예외.
 */
public class MisMatchedAuthorityException extends RuntimeException {

  public MisMatchedAuthorityException() {
  }

  public MisMatchedAuthorityException(String message) {
    super(message);
  }
}
