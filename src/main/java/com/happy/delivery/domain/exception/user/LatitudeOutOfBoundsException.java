package com.happy.delivery.domain.exception.user;

/**
 * LatitudeOutOfBoundsException.
 * 위도가 범위를 벗어났을 때.
 */
public class LatitudeOutOfBoundsException extends RuntimeException {

  public LatitudeOutOfBoundsException() {
  }

  public LatitudeOutOfBoundsException(String message) {
    super(message);
  }
}
