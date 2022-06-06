package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.restaurant.EmptyRestaurantListException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestaurantExceptionHandler.
 * 레스토랑 관련 exception 처리 class.
 */
@RestControllerAdvice
public class RestaurantExceptionHandler {

  /**
   * MyBatis 에서 Redis 로 가져올 음식점 데이터가 없는 경우.
   */
  @ExceptionHandler(EmptyRestaurantListException.class)
  public ApiResponse<?> emptyRestaurantListException(EmptyRestaurantListException ex) {
    return ApiResponse.fail("EMPTY_RESTAURANT_LIST", ex.getMessage());
  }
}
