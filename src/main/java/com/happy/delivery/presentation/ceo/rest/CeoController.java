package com.happy.delivery.presentation.ceo.rest;

import com.happy.delivery.application.ceo.CeoService;
import com.happy.delivery.application.ceo.result.CeoResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.presentation.ceo.request.CeoSigninRequest;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.user.request.SignupRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CeoController.
 */
@RestController
@RequestMapping("/ceo")
public class CeoController {
  private final CeoService ceoService;

  @Autowired
  public CeoController(CeoService ceoService) {
    this.ceoService = ceoService;
  }


  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/ceoSignup")
  public ApiResponse signup(@Valid @RequestBody CeoSigninRequest request) {
    CeoResult ceoResult = ceoService.ceoSignup(request.toCommand());
    return ApiResponse.success(ceoResult);
  }

  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/ceoSignin")
  public ApiResponse ceoSignin(@Valid @RequestBody CeoSigninRequest request) {
    CeoResult ceoResult = ceoService.ceoSignin(request.toCommand());
    return ApiResponse.success(ceoResult);
  }
}
