package com.happy.delivery.presentation.ceo.handler;

import com.happy.delivery.domain.exception.ceo.EmailDuplicateException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CeoExceptionHandler.
 */
@RestControllerAdvice
public class CeoExceptionHandler {

  @ExceptionHandler(EmailDuplicateException.class)
  public ApiResponse<?> emailDuplicateException(EmailDuplicateException ex) {
    return ApiResponse.fail("EMAIL_ALREADY_EXISTED", ex.getMessage());
  }

}
