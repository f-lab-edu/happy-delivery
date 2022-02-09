package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.response.SignResponse;
import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.UserServiceV1;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.SignupRequest;
import com.happy.delivery.presentation.user.response.SignupResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(@RequestBody SignupRequest request) {
        SignResponse signResponse = userService.signup(request.toCommand(request));
        return ApiResponse.success(signResponse);
    }

}
