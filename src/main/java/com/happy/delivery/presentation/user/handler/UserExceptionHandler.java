package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NoUserIdMatchedException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.domain.exception.user.UserNotExistedException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

//유효성검사 오류
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ApiResponse.fail("METHOD_ARGUMENT_NOT_VALID", String.valueOf(errors));
    }

    //이메일 검증유무
    @ExceptionHandler(UserAlreadyExistedException.class)
    public ApiResponse<?> userAlreadyExistedException(UserAlreadyExistedException ex) {
        return ApiResponse.fail("USER_ALREADY_EXISTED", ex.getMessage());
    }

    //로그인시 이메일 불일치
    @ExceptionHandler(EmailIsNotMatchException.class)
    public ApiResponse<?> emailIsNotMatchException(EmailIsNotMatchException ex){
        return ApiResponse.fail("EMAIL_IS_NOT_MATCHED", ex.getMessage());
    }

    //로그인시 패스워드 불일치
    @ExceptionHandler(PasswordIsNotMatchException.class)
    public ApiResponse<?> passwordIsNotMatchException(PasswordIsNotMatchException ex){
        return ApiResponse.fail("PASSWORD_IS_NOT_MATCHED", ex.getMessage());
    }

    //식별자 없음
    @ExceptionHandler(NoUserIdMatchedException.class)
    public ApiResponse<?> noUserIdMatchedException(NoUserIdMatchedException ex){
        return ApiResponse.fail("USER_ID_IS_NOT_MATCHED", ex.getMessage());
    }

    //주소가 없음
    @ExceptionHandler(UserNotExistedException.class)
    public ApiResponse<?> userNotExistedException(UserNotExistedException ex) {
        return ApiResponse.fail("USER_NOT_EXISTED", ex.getMessage());
    }
}
