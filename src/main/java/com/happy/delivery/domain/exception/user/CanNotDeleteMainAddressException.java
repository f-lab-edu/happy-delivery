package com.happy.delivery.domain.exception.user;

/**
 * CanNotDeleteMainAddressException.
 */
public class CanNotDeleteMainAddressException extends RuntimeException {

  public CanNotDeleteMainAddressException() {
  }

  public CanNotDeleteMainAddressException(String message) {
    super(message);
  }
}
