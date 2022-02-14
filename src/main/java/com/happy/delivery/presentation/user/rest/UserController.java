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

@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")

    public ApiResponse<SignupResult> signup(@Valid @RequestBody SignupRequest request){
        SignupResult signupResult = userService.signup(request.toCommand());
        return ApiResponse.success(signupResult);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/signin")
    public ApiResponse<String> signin(@Valid @RequestBody SigninRequest request) {
        String signinResult = userService.signin(request.toCommand());
        return ApiResponse.success(signinResult + " 로그인 성공 했습니다.");
    }
}
