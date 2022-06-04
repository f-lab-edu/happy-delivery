package com.happy.delivery.domain.exception.restaurant;

/**
 * EmptyRestaurantListException.
 * MySQL DB에 저장된 레스토랑 데이터가 없는 경우 발생하는 예외.
 */
public class EmptyRestaurantListException extends RuntimeException {

  public EmptyRestaurantListException() {
  }

  public EmptyRestaurantListException(String message) {
    super(message);
  }
}
