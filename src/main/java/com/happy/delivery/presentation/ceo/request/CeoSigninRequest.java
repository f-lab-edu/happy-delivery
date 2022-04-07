package com.happy.delivery.presentation.ceo.request;

public class SigninRequest {
  private Long id;
  private String email;
  private String password;
  private String name;
  private String phoneNumber;

  public SigninRequest(Long id, String email, String password, String name,
      String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
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

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
