package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.user.CanNotDeleteMainAddressException;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.LatitudeOutOfBoundsException;
import com.happy.delivery.domain.exception.user.LongitudeOrLatitudeNullPointException;
import com.happy.delivery.domain.exception.user.LongitudeOutOfBoundsException;
import com.happy.delivery.domain.exception.user.NoUserException;
import com.happy.delivery.domain.exception.user.NotAuthorizedException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.PointWktReaderParseException;
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
   * 세션 아이디가 없을 경우.
   */
  @ExceptionHandler(NoUserException.class)
  public ApiResponse<?> noUserIdException(NoUserException ex) {
    return ApiResponse.fail("NO_USER_ID.", ex.getMessage());
  }


  /**
   * 저장된 주소가 없는 경우.
   */
  @ExceptionHandler(UserAddressNotExistedException.class)
  public ApiResponse<?> userAddressNotExistedException(UserAddressNotExistedException ex) {
    return ApiResponse.fail("USER_ADDRESS_NOT_EXISTED", ex.getMessage());
  }

  /**
   * 권한이 없는 경우.
   */
  @ExceptionHandler(NotAuthorizedException.class)
  public ApiResponse<?> notAuthorizedException(NotAuthorizedException ex) {
    return ApiResponse.fail("NOT_AUTHORIZED", ex.getMessage());
  }

  /**
   * 현재 주소로 설정된 주소정보를 삭제하려고 한 경우.
   * user에 들어있는 main address의 정보를 삭제하려고 한 경우.
   */
  @ExceptionHandler(CanNotDeleteMainAddressException.class)
  public ApiResponse<?> canNotDeleteMainAddressException(CanNotDeleteMainAddressException ex) {
    return ApiResponse.fail("CAN_NOT_DELETE_MAIN_ADDRESS", ex.getMessage());
  }

  /**
   * 경도나 위도를 빠트린 경우.
   */
  @ExceptionHandler(LongitudeOrLatitudeNullPointException.class)
  public ApiResponse<?> longitudeOrLatitudeNullPointException(
      LongitudeOrLatitudeNullPointException ex) {
    return ApiResponse.fail("LONGITUDE_OR_LATITUDE_NULL_VALUES", ex.getMessage());
  }

  /**
   * 경도가 범위를 벗어난 경우.
   */
  @ExceptionHandler(LongitudeOutOfBoundsException.class)
  public ApiResponse<?> longitudeOutOfBoundsException(LongitudeOutOfBoundsException ex) {
    return ApiResponse.fail("LONGITUDE_OUT_OF_BOUNDS", ex.getMessage());
  }

  /**
   * 위도가 범위를 벗어난 경우.
   */
  @ExceptionHandler(LatitudeOutOfBoundsException.class)
  public ApiResponse<?> latitudeOutOfBoundsException(LatitudeOutOfBoundsException ex) {
    return ApiResponse.fail("LATITUDE_OUT_OF_BOUNDS", ex.getMessage());
  }

  /**
   * AddressVo에서 Point값 파싱에 문제가 생긴 경우.
   */
  @ExceptionHandler(PointWktReaderParseException.class)
  public ApiResponse<?> pointWktReaderParseException(PointWktReaderParseException ex) {
    return ApiResponse.fail("POINT_WKT_READER_PARSE_EXCEPTION", ex.getMessage());
  }
}
