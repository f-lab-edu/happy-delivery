package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.infra.annotation.UserLoginCheck;
import com.happy.delivery.infra.util.SessionUtil;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.AddressRequest;
import com.happy.delivery.presentation.user.request.MyAccountRequest;
import com.happy.delivery.presentation.user.request.PasswordUpdateRequest;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * UserController Constructor.
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/signup")
  public ApiResponse signup(@Valid @RequestBody SignupRequest request) {
    UserResult userResult = userService.signup(request.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * signin.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/signin")
  public ApiResponse signin(@Valid @RequestBody SigninRequest request,
      HttpSession httpSession) {
    UserResult userResult = userService.signin(request.toCommand());
    SessionUtil.setLoginId(httpSession, userResult.getId());
    return ApiResponse.success(userResult);
  }

  /**
   * UserController logout.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/logout")
  public void logout(HttpSession httpSession) {
    SessionUtil.clear(httpSession);
  }

  /**
   * myAccount view.
   * 계정 보여주기.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/my-account")
  public ApiResponse getMyAccount(HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    UserResult myAccount = userService.getMyAccount(userId);
    return ApiResponse.success(myAccount);
  }

  /**
   * myAccount update.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping("/my-account")
  public ApiResponse updateMyAccount(@Valid @RequestBody MyAccountRequest myAccountRequest,
      HttpSession httpSession) {
    myAccountRequest.addSessionLoginId(SessionUtil.getLoginId(httpSession));
    UserResult myaccountResult = userService.updateMyAccount(myAccountRequest.toCommand());
    return ApiResponse.success(myaccountResult);
  }

  /**
   * my-account/password.
   * update password.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/my-account/password")
  public ApiResponse updatePassword(@Valid @RequestBody PasswordUpdateRequest request,
      HttpSession httpSession) {
    UserResult userResult =
        userService.updatePassword(SessionUtil.getLoginId(httpSession), request.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * myAccount delete.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/my-account")
  public ApiResponse deleteMyAccount(@Valid HttpSession httpSession) {
    userService.deleteMyAccount(SessionUtil.getLoginId(httpSession));
    SessionUtil.clear(httpSession);
    return ApiResponse.success(null);
  }

  /**
   * UserController PostMapping /addresses.
   * 주소 저장
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/addresses")
  public ApiResponse saveAddress(@Valid @RequestBody AddressRequest addressRequest,
      HttpSession httpSession) {
    UserAddressResult userAddressResult = userService.saveAddress(
        SessionUtil.getLoginId(httpSession), addressRequest.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * UserController GetMapping /addresses.
   * 주소 목록 가져오기.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/addresses")
  public ApiResponse getListOfAllAddresses(HttpSession httpSession) {
    List<UserAddressResult> listOfAllAddresses =
        userService.getListOfAllAddresses(SessionUtil.getLoginId(httpSession));
    return ApiResponse.success(listOfAllAddresses);
  }

  /**
   * UserController update /addresses.
   * 주소 수정.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/addresses/{addressId}")
  public ApiResponse updateAddress(@PathVariable Long addressId, HttpSession httpSession,
      @Valid @RequestBody AddressRequest addressCommand) {
    UserAddressResult userAddressResult = userService.updateAddress(addressId,
            SessionUtil.getLoginId(httpSession), addressCommand.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * updateMainAddress.
   * 현재 주소 설정.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @PatchMapping("/addresses/main/{addressId}")
  public ApiResponse updateMainAddress(@PathVariable Long addressId, HttpSession httpSession) {
    UserAddressResult userAddressResult =
        userService.updateMainAddress(SessionUtil.getLoginId(httpSession), addressId);
    return ApiResponse.success(userAddressResult);
  }

  /**
   * deleteAddress.
   * 주소 삭제.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/addresses/{addressId}")
  public ApiResponse deleteAddress(@PathVariable Long addressId, HttpSession httpSession) {
    userService.deleteAddress(addressId, SessionUtil.getLoginId(httpSession));
    return ApiResponse.success(null);
  }
}