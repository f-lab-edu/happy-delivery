package com.happy.delivery.domain.exception.restaurant;

/**
 * NoRestaurantsInThatCategoryException.
 * 근처 식당 중에 해당 카테고리에 속하는 값이 없음.
 */
public class NoRestaurantsInThatCategoryException extends RuntimeException {

  public NoRestaurantsInThatCategoryException() {
  }

  public NoRestaurantsInThatCategoryException(String message) {
    super(message);
  }
}
