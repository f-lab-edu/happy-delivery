package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.presentation.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> validationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    //이메일 검증유무
    @ExceptionHandler(UserAlreadyExistedException.class)
    public ApiResponse<?> handle(UserAlreadyExistedException ex) {
        return ApiResponse.fail("USER_ALREADY_EXISTED", null);
    }

    //로그인시 이메일 불일치
    @ExceptionHandler(EmailIsNotMatchException.class)
    public ApiResponse<?> handle(EmailIsNotMatchException ex){
        return ApiResponse.fail("EMAIL_IS_NOT_MATCHED", null);
    }

    //로그인시 패스워드 불일치
    @ExceptionHandler(PasswordIsNotMatchException.class)
    public ApiResponse<?> handle(PasswordIsNotMatchException ex){
        return ApiResponse.fail("PASSWORD_IS_NOT_MATCHED", null);
    }

}
