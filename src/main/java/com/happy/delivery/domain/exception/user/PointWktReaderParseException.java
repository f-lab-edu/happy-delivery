package com.happy.delivery.domain.exception.user;

/**
 * PointWktReaderParseException.
 * AddressVo에서 Point값 파싱에 문제가 생긴 경우.
 */
public class PointWktReaderParseException extends RuntimeException {

  public PointWktReaderParseException() {
  }

  public PointWktReaderParseException(String message) {
    super(message);
  }
}
