package com.happy.delivery.application.user.command;

import com.happy.delivery.domain.enumeration.Authority;

/**
 * SignupCommand.
 */
public class SignupCommand {

  private String email;
  private String password;
  private String name;
  private String phoneNumber;
  private Authority authority;

  /**
   * SignupCommand Constructor.
   */
  public SignupCommand(String email, String password, String name, String phoneNumber,
      Authority authority) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Authority getAuthority() {
    return authority;
  }
}
