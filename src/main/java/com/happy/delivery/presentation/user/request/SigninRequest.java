package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.SigninCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * SigninRequest.
 */
public class SigninRequest {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  /**
   * SigninRequest Constructor.
   */
  public SigninRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * SigninRequest -> SigninCommand.
   */
  public SigninCommand toCommand() {
    return new SigninCommand(
        this.email,
        this.password
    );
  }
}
