package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.application.user.UserService;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    /*
    UserService 인터페이스가 필요한지 확인 필요.
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse<SignupResult> signup(@Valid @RequestBody SignupRequest request) throws NoSuchAlgorithmException {
        //repository에 저장
        SignupResult signupResult = userService.signup(request.toCommand());
        return ApiResponse.success(signupResult);
    }

    @PostMapping("/signin")
    public ApiResponse<SignupResult> signin(@Valid @RequestBody SigninRequest request) {
        log.info("request = {}", request);
        userService.signin(request.toCommand());
        return ApiResponse.success(request.toString());
    }
}
