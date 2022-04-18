package com.happy.delivery.domain.exception.user;

/**
 * LongitudeOutOfBoundsException.
 * 경도가 범위를 벗어났을 때.
 */
public class LongitudeOutOfBoundsException extends RuntimeException {

  public LongitudeOutOfBoundsException() {
  }

  public LongitudeOutOfBoundsException(String message) {
    super(message);
  }
}
