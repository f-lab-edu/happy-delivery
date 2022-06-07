package com.happy.delivery.domain.exception.restaurant;

/**
 * NearbyRestaurantNotExistException.
 * 배달 가능한 근처 레스토랑이 없음.
 */
public class NearbyRestaurantNotExistException extends RuntimeException {

  public NearbyRestaurantNotExistException() {
  }

  public NearbyRestaurantNotExistException(String message) {
    super(message);
  }
}
