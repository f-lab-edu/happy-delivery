package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.ceo.EmailDuplicateException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * CeoExceptionHandler.
 */
public class CeoExceptionHandler {


  @ExceptionHandler(EmailDuplicateException.class)
  public ApiResponse<?> EmailDuplicateException(EmailDuplicateException ex) {
    return ApiResponse.fail("Email_ALREADY_EXISTED", ex.getMessage());
  }
}
