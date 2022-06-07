package com.happy.delivery.presentation.restaurant.handler;

import com.happy.delivery.domain.exception.restaurant.EmptyRestaurantListException;
import com.happy.delivery.domain.exception.restaurant.NearbyRestaurantNotExistException;
import com.happy.delivery.domain.exception.restaurant.NoRestaurantsInThatCategoryException;
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
   * EmptyRestaurantListException.
   * MyBatis 에서 Redis 로 가져올 음식점 데이터가 없는 경우.
   */
  @ExceptionHandler(EmptyRestaurantListException.class)
  public ApiResponse<?> emptyRestaurantListException(EmptyRestaurantListException ex) {
    return ApiResponse.fail("EMPTY_RESTAURANT_LIST", ex.getMessage());
  }

  /**
   * NearbyRestaurantNotExistException.
   * Redis 로 계산한 결과 근처에 배달 가능한 음식점이 없는 경우.
   */
  @ExceptionHandler(NearbyRestaurantNotExistException.class)
  public ApiResponse<?> nearbyRestaurantNotExistException(NearbyRestaurantNotExistException ex) {
    return ApiResponse.fail("NEARBY_RESTAURANT_IS_NOT_EXIST", ex.getMessage());
  }

  /**
   * NearbyRestaurantNotExistException.
   * Redis 로 계산한 결과 근처에 배달 가능한 음식점이 없는 경우.
   */
  @ExceptionHandler(NoRestaurantsInThatCategoryException.class)
  public ApiResponse<?> noObjectsInThatCategoryException(NoRestaurantsInThatCategoryException ex) {
    return ApiResponse.fail("NO_OBJECTS_IN_THAT_CATEGORY", ex.getMessage());
  }
}
