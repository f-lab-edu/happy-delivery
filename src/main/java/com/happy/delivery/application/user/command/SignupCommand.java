package com.happy.delivery.application.user.command;

/**
 * SignupCommand.
 */
public class SignupCommand {

  private String email;
  private String password;
  private String name;
  private String phoneNumber;

  /**
   * SignupCommand constructor.
   */
  public SignupCommand(String email, String password, String name, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
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

}
