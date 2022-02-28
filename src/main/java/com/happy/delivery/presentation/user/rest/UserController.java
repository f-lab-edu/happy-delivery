package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.infra.util.SessionUtil;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.MyAccountRequest;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * UserController.
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger log = LoggerFactory.getLogger(UserController.class);
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

  /**
   * signin.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/signin")
  public ApiResponse<UserResult> signin(@Valid @RequestBody SigninRequest request,
      HttpSession httpSession) {
    UserResult userResult = userService.signin(request.toCommand());
    if (userResult != null) {
      SessionUtil.setLoginId(httpSession, userResult.getId());
    }
    return ApiResponse.success(userResult);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/logout")
  public void logout(HttpSession httpSession) {
    SessionUtil.clear(httpSession);
  }


  /**
   * myAccount view.
   */
  @GetMapping("/myaccount")
  public ApiResponse<UserResult> getMyAccount(HttpSession httpSession) {
    Long sessionId = SessionUtil.getLoginId(httpSession);
    UserResult myAccount = userService.getMyAccount(sessionId);
    return ApiResponse.success(myAccount);
  }

  /**
   * myAccount update.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping("/myaccount")
  public ApiResponse<UserResult> updateMyAccount(@Valid @RequestBody
      MyAccountRequest myAccountRequest, HttpSession httpSession) {
    Long sessionId = SessionUtil.getLoginId(httpSession);
    MyAccountRequest myAccountInfo = new MyAccountRequest(
        sessionId,
        myAccountRequest.getEmail(),
        myAccountRequest.getName(),
        myAccountRequest.getPhoneNumber()
    );
    UserResult myaccountResult = userService.updateMyAccount(myAccountInfo.toCommand());
    return ApiResponse.success(myaccountResult);
  }
}
