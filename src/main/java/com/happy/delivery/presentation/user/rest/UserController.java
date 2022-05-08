package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.AddressRequest;
import com.happy.delivery.presentation.user.request.MyAccountRequest;
import com.happy.delivery.presentation.user.request.PasswordUpdateRequest;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  private final AuthorizationService authorizationService;

  /**
   * UserController Constructor.
   */
  @Autowired
  public UserController(UserService userService, AuthorizationService authorizationService) {
    this.userService = userService;
    this.authorizationService = authorizationService;
  }

  /**
   * signup.
   * 회원가입 기능.
   * 추가해야 하는 것 :: enum 값 (Status) 을 DB 에 저장해야 함.
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/signup")
  public ApiResponse signup(@Valid @RequestBody SignupRequest request) {
    UserResult userResult = userService.signup(request.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * signin.
   * 로그인.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/signin")
  public ApiResponse signin(@Valid @RequestBody SigninRequest request) {
    UserResult userResult = userService.signin(request.toCommand());
    String token = authorizationService.login(
        new AuthorizationCommand(userResult.getId(), userResult.getAuthority()));
    userResult.addCurrentUserToken(token);
    return ApiResponse.success(userResult);
  }

  /**
   * UserController logout.
   * 로그아웃.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {
    authorizationService.logout(request);
  }

  /**
   * myAccount view.
   * 계정 정보 가져오기.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/my-account")
  public ApiResponse getMyAccount(HttpServletRequest request) {
    Long userId = getCurrentUserId(request);
    UserResult myAccount = userService.getMyAccount(userId);
    return ApiResponse.success(myAccount);
  }

  /**
   * myAccount update.
   * 계정 정보 수정하기.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping("/my-account")
  public ApiResponse updateMyAccount(HttpServletRequest request,
      @Valid @RequestBody MyAccountRequest myAccountRequest) {
    Long userId = getCurrentUserId(request);
    myAccountRequest.addCurrentUserId(userId);
    UserResult myAccountResult = userService.updateMyAccount(myAccountRequest.toCommand());
    return ApiResponse.success(myAccountResult);
  }

  /**
   * updatePassword.
   * 비밀번호 변경.
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/my-account/password")
  public ApiResponse updatePassword(HttpServletRequest request,
      @Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
    Long userId = getCurrentUserId(request);
    UserResult userResult = userService.updatePassword(userId, passwordUpdateRequest.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * deleteMyAccount.
   * 계정 탈퇴.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/my-account")
  public ApiResponse deleteMyAccount(HttpServletRequest request) {
    userService.deleteMyAccount(getCurrentUserId(request));
    authorizationService.logout(request);
    return ApiResponse.success(null);
  }

  /**
   * saveAddress.
   * 주소 저장
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/addresses")
  public ApiResponse saveAddress(HttpServletRequest request,
      @Valid @RequestBody AddressRequest addressRequest) {
    Long userId = getCurrentUserId(request);
    UserAddressResult userAddressResult =
        userService.saveAddress(userId, addressRequest.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * getListOfAllAddresses.
   * 주소 목록 가져오기.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/addresses")
  public ApiResponse getListOfAllAddresses(HttpServletRequest request) {
    Long userId = getCurrentUserId(request);
    List<UserAddressResult> listOfAllAddresses = userService.getListOfAllAddresses(userId);
    return ApiResponse.success(listOfAllAddresses);
  }

  /**
   * updateAddress.
   * 주소 수정.
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/addresses/{addressId}")
  public ApiResponse updateAddress(HttpServletRequest request, @PathVariable Long addressId,
      @Valid @RequestBody AddressRequest addressCommand) {
    Long userId = getCurrentUserId(request);
    UserAddressResult userAddressResult =
        userService.updateAddress(addressId, userId, addressCommand.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * updateMainAddress.
   * 현재 주소 설정.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PatchMapping("/addresses/main/{addressId}")
  public ApiResponse updateMainAddress(HttpServletRequest request, @PathVariable Long addressId) {
    Long userId = getCurrentUserId(request);
    UserAddressResult userAddressResult = userService.updateMainAddress(userId, addressId);
    return ApiResponse.success(userAddressResult);
  }

  /**
   * deleteAddress.
   * 주소 삭제.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/addresses/{addressId}")
  public ApiResponse deleteAddress(HttpServletRequest request, @PathVariable Long addressId) {
    Long userId = getCurrentUserId(request);
    userService.deleteAddress(addressId, userId);
    return ApiResponse.success(null);
  }

  /**
   * getCurrentUserId.
   * 현재 사용자의 식별자를 토큰, 세션 등을 이용해 가져오는 메서드.
   */
  private Long getCurrentUserId(HttpServletRequest request) {
    return authorizationService.getCurrentUser(request).getMemberId();
  }
}