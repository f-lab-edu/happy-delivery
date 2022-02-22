package com.happy.delivery.application.user.command;

public class SigninCommand {

  private String email;
  private String password;

  public SigninCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
