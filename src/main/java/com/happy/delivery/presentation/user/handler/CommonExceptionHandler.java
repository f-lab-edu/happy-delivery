package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.common.NotLoggedInException;
import com.happy.delivery.domain.exception.common.NullTokenValueException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CommonExceptionHandler.
 */
@RestControllerAdvice
public class CommonExceptionHandler {

  /**
   * notLoggedInException.
   * 로그인이 되어 있지 않은 경우 예외 발생.
   */
  @ExceptionHandler(NotLoggedInException.class)
  public ApiResponse<?> notLoggedInException(NotLoggedInException ex) {
    return ApiResponse.fail("NOT_LOGGED_IN", ex.getMessage());
  }

  /**
   * NullTokenValueException.
   * token 값이 들어오지 않은 경우 예외 발생.
   */
  @ExceptionHandler(NullTokenValueException.class)
  public ApiResponse<?> nullTokenValueException(NullTokenValueException ex) {
    return ApiResponse.fail("NULL_TOKEN_VALUE", ex.getMessage());
  }
}
