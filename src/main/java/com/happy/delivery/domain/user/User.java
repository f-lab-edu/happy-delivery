package com.happy.delivery.domain.user;

/**
 * User Domain.
 */
public class User {

  private Long id;
  private final String email;
  private final String password;
  private final String name;
  private final String phoneNumber;

  /**
   * User signin Constructor.
   */
  public User(String email, String password, String name, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * User not signin Constructor.
   */
  public User(Long id, String email, String password, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
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

