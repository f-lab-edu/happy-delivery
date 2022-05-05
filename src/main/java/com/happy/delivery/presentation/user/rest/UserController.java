package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.application.common.command.AuthorizationCommand;
import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.enumeration.Authority;
import com.happy.delivery.infra.annotation.UserLoginCheck;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.AddressRequest;
import com.happy.delivery.presentation.user.request.MyAccountRequest;
import com.happy.delivery.presentation.user.request.PasswordUpdateRequest;
import com.happy.delivery.presentation.user.request.SigninRequest;
import com.happy.delivery.presentation.user.request.SignupRequest;
import java.util.List;
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
   * 로그인 기능.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/signin")
  public ApiResponse signin(@Valid @RequestBody SigninRequest request) {
    UserResult userResult = userService.signin(request.toCommand());
    // TokenService 가 아닌 것 같긴한데 일단 이렇게 만들어보자.
    // Status.USER 값을 userResult 에서 가져오도록 만들기.
    authorizationService.login(new AuthorizationCommand(userResult.getId(), Authority.USER));
    return ApiResponse.success(userResult);
  }

  /**
   * UserController logout.
   * 로그아웃.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/logout")
  public void logout() {
    authorizationService.logout();
  }

  /**
   * myAccount view.
   * 계정 정보 가져오기.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/my-account")
  public ApiResponse getMyAccount() {
    UserResult myAccount = userService.getMyAccount(getCurrentUserId());
    return ApiResponse.success(myAccount);
  }

  /**
   * myAccount update.
   * 계정 정보 수정하기.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping("/my-account")
  public ApiResponse updateMyAccount(@Valid @RequestBody MyAccountRequest myAccountRequest) {
    myAccountRequest.addLoginId(getCurrentUserId());
    UserResult myAccountResult = userService.updateMyAccount(myAccountRequest.toCommand());
    return ApiResponse.success(myAccountResult);
  }

  /**
   * my-account/password.
   * update password.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/my-account/password")
  public ApiResponse updatePassword(@Valid @RequestBody PasswordUpdateRequest request) {
    UserResult userResult = userService.updatePassword(getCurrentUserId(), request.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * myAccount delete.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/my-account")
  public ApiResponse deleteMyAccount() {
    userService.deleteMyAccount(getCurrentUserId());
    authorizationService.logout();
    return ApiResponse.success(null);
  }

  /**
   * UserController PostMapping /addresses.
   * 주소 저장
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/addresses")
  public ApiResponse saveAddress(@Valid @RequestBody AddressRequest addressRequest) {
    UserAddressResult userAddressResult =
        userService.saveAddress(getCurrentUserId(), addressRequest.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * UserController GetMapping /addresses.
   * 주소 목록 가져오기.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/addresses")
  public ApiResponse getListOfAllAddresses() {
    List<UserAddressResult> listOfAllAddresses =
        userService.getListOfAllAddresses(getCurrentUserId());
    return ApiResponse.success(listOfAllAddresses);
  }

  /**
   * UserController update /addresses.
   * 주소 수정.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/addresses/{addressId}")
  public ApiResponse updateAddress(@PathVariable Long addressId,
      @Valid @RequestBody AddressRequest addressCommand) {
    UserAddressResult userAddressResult =
        userService.updateAddress(addressId, getCurrentUserId(), addressCommand.toCommand());
    return ApiResponse.success(userAddressResult);
  }

  /**
   * updateMainAddress.
   * 현재 주소 설정.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @PatchMapping("/addresses/main/{addressId}")
  public ApiResponse updateMainAddress(@PathVariable Long addressId) {
    UserAddressResult userAddressResult =
        userService.updateMainAddress(getCurrentUserId(), addressId);
    return ApiResponse.success(userAddressResult);
  }

  /**
   * deleteAddress.
   * 주소 삭제.
   */
  @UserLoginCheck
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/addresses/{addressId}")
  public ApiResponse deleteAddress(@PathVariable Long addressId) {
    userService.deleteAddress(addressId, getCurrentUserId());
    return ApiResponse.success(null);
  }

  /**
   * getCurrentUserId.
   * 현재 사용자의 식별자를 토큰, 세션 등을 이용해 가져오는 메서드.
   */
  private Long getCurrentUserId() {
    return authorizationService.getCurrentUser().getMemberId();
  }
}