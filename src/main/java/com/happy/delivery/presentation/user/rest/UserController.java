package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.application.user.UserService;
import com.happy.delivery.infra.util.SessionUtil;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ApiResponse<UserResult> signup(@Valid @RequestBody SignupRequest request) {
        UserResult userResult = userService.signup(request.toCommand());
        return ApiResponse.success(userResult);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/signin")
    public ApiResponse<UserResult> signin(@Valid @RequestBody SigninRequest request,
            HttpSession httpSession) {
        UserResult userResult = userService.signin(request.toCommand());
        if (userResult != null) {
            SessionUtil.setLoginId(httpSession, request.getId());
        }
        return ApiResponse.success(userResult);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        SessionUtil.clear(httpSession);
    }
}
