package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NoUserIdMatchedException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAddressNotExistedException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * UserExceptionHandler.
 */
@RestControllerAdvice
public class UserExceptionHandler {

  /**
   * 유효성 검사에 맞지 않은경우.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
    return ApiResponse.fail("METHOD_ARGUMENT_NOT_VALID", String.valueOf(errors));
  }

  /**
   * 이미 사용중인 계정이 있을 경우.
   */
  @ExceptionHandler(UserAlreadyExistedException.class)
  public ApiResponse<?> userAlreadyExistedException(UserAlreadyExistedException ex) {
    return ApiResponse.fail("USER_ALREADY_EXISTED", ex.getMessage());
  }

  /**
   * 로그인시 이메일 불일치.
   */
  @ExceptionHandler(EmailIsNotMatchException.class)
  public ApiResponse<?> emailIsNotMatchException(EmailIsNotMatchException ex) {
    return ApiResponse.fail("EMAIL_IS_NOT_MATCHED", ex.getMessage());
  }

  /**
   * 로그인시 패스워드 불일치.
   */
  @ExceptionHandler(PasswordIsNotMatchException.class)
  public ApiResponse<?> passwordIsNotMatchException(PasswordIsNotMatchException ex) {
    return ApiResponse.fail("PASSWORD_IS_NOT_MATCHED", ex.getMessage());
  }

  /**
   * 로그인 id가 없는경우.
   */
  @ExceptionHandler(NoUserIdMatchedException.class)
  public ApiResponse<?> noUserIdMatchedException(NoUserIdMatchedException ex) {
    return ApiResponse.fail("USER_ID_IS_NOT_MATCHED", ex.getMessage());
  }

  /**
   * 저장된 주소가 없는 경우.
   */
  @ExceptionHandler(UserAddressNotExistedException.class)
  public ApiResponse<?> UserAddressNotExistedException(UserAddressNotExistedException ex) {
    return ApiResponse.fail("USER_ADDRESS_NOT_EXISTED", ex.getMessage());
  }
}