package com.happy.delivery.presentation.user.handler;

import com.happy.delivery.domain.exception.user.AuthenticationFailedException;
import com.happy.delivery.domain.exception.user.EmailAlreadyUserException;
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
    @ExceptionHandler(EmailAlreadyUserException.class)
    public ApiResponse<?> handle(EmailAlreadyUserException ex) {
        return ApiResponse.fail("EMAIL_ALREADY_USED", null);
    }

    //Email 또는 Password 인증 실패
    @ExceptionHandler(AuthenticationFailedException.class)
    public String handle(AuthenticationFailedException ex){
        return "ID_OR_PASSWORD_AUTHENTICATION_FAILED";
    }

}
