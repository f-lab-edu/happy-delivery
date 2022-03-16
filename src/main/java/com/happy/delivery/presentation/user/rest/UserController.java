package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.user.UserService;
import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.exception.user.NoUserIdException;
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
    if (userResult != null) {
      SessionUtil.setLoginId(httpSession, userResult.getId());
    }
    return ApiResponse.success(userResult);
  }

  /**
   * UserController logout.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/logout")
  public void logout(HttpSession httpSession) {
    SessionUtil.clear(httpSession);
  }

  /**
   * myAccount view.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/my-account")
  public ApiResponse<UserResult> getMyAccount(HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    UserResult myAccount = userService.getMyAccount(userId);
    return ApiResponse.success(myAccount);
  }

  /**
   * myAccount update.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping("/my-account")
  public ApiResponse<UserResult> updateMyAccount(@Valid @RequestBody
      MyAccountRequest myAccountRequest, HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    MyAccountRequest myAccountInfo = new MyAccountRequest(
        userId,
        myAccountRequest.getEmail(),
        myAccountRequest.getName(),
        myAccountRequest.getPhoneNumber()
    );
    UserResult myaccountResult = userService.updateMyAccount(myAccountInfo.toCommand());
    return ApiResponse.success(myaccountResult);
  }

  /**
   * myAccount delete.
   */
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("my-account")
  public ApiResponse<UserResult> deleteMyAccount(@Valid HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    userService.deleteMyAccount(userId);
    SessionUtil.clear(httpSession);
    return ApiResponse.success("DELETE_MY_ACCOUNT");
  }

  /**
   * UserController my-account/password.
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/my-account/password")
  public ApiResponse updatePassword(@Valid @RequestBody PasswordUpdateRequest request,
      HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    UserResult userResult = userService.updatePassword(userId,
        request.toCommand());
    return ApiResponse.success(userResult);
  }

  /**
   * UserController PostMapping /addresses. 주소 저장 및 추가 || 새로고침하면 중복 저장되는 문제 ||
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/addresses")
  public ApiResponse saveAddress(@Valid @RequestBody AddressRequest address,
      HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    UserAddressResult userAddressResult = userService.saveAddress(
        address.toCommand(null, SessionUtil.getLoginId(httpSession)));
    if (userAddressResult != null) {
      SessionUtil.setAddressId(httpSession, userAddressResult.getId());
    }
    return ApiResponse.success(userAddressResult);
  }

  /**
   * UserController GetMapping /addresses. 주소 목록 가져오기
   */
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping("/addresses")
  public ApiResponse getListOfAllAddresses(HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    List<UserAddressResult> listOfAllAddresses = userService
        .getListOfAllAddresses(userId);
    return ApiResponse.success(listOfAllAddresses);
  }

  /**
   * UserController update /addresses. 주소 수정
   */
  @ResponseStatus(code = HttpStatus.CREATED)
  @PatchMapping("/addresses/{addressId}")
  public ApiResponse updateAddress(@PathVariable Long addressId,
      @Valid @RequestBody AddressRequest addressRequest, HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    userService.updateAddress(addressRequest.toCommand(addressId, userId));
    return ApiResponse.success("UPDATING_ADDRESS_SUCCESS");
  }

  /**
   * UserController delete /addresses. 주소 삭제
   */
  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping("/addresses/{addressId}")
  public ApiResponse deleteAddress(@PathVariable Long addressId, HttpSession httpSession) {
    Long userId = SessionUtil.getLoginId(httpSession);
    sessionIsNotExist(userId);
    UserAddressResult userAddressResult = userService.deleteAddress(
        new AddressCommand(addressId, userId, null, null)
    );
    return ApiResponse.success(userAddressResult);
  }

  private void sessionIsNotExist(Long userId) {
    if (userId == null) {
      throw new NoUserIdException("로그인이 필요한 서비스입니다.");
    }
  }
}