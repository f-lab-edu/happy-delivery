package com.happy.delivery.presentation.ceo.request;

import com.happy.delivery.application.ceo.command.CeoSigninCommand;
import com.happy.delivery.application.user.command.SigninCommand;

/**
 * CeoSigninRequest.
 */
public class CeoSigninRequest {
  private Long id;
  private String email;
  private String password;

  /**
   * CeoSigninRequest Constructor.
   */
  public CeoSigninRequest(Long id, String email, String password, String name,
      String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  /**
   * CeoSigninRequest toCommand.
   */
  public CeoSigninCommand toCommand() {
    return new CeoSigninCommand(
        this.id,
        this.email,
        this.password
    );
  }
}
