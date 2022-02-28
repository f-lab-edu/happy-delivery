package com.happy.delivery.domain.user;

import com.happy.delivery.infra.encoder.EncryptMapper;

/**
 * User.
 */
public class User {

  private Long id;
  private String email;
  private String password;
  private String name;
  private String phoneNumber;

  /**
   * 회원가입 할 때 사용.
   */
  public User(String email, String password, String name, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * 보통의 경우.
   */
  public User(Long id, String email, String password, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * myaccount update할때.
   */
  public void setMyAccountUpdate(String email, String name, String phoneNumber) {
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public void changePassword(EncryptMapper encryptMapper, String changedPassword) {
    this.password = encryptMapper.encoder(changedPassword);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

