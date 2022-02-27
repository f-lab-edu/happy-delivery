package com.happy.delivery.domain.exception.user;

/**
 * UserNotExistedException.
 */
public class UserAddressNotExistedException extends RuntimeException {

  public UserAddressNotExistedException() {
  }

  public UserAddressNotExistedException(String message) {
    super(message);
  }
}
