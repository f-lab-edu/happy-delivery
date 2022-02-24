package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.User;

/**
 * UserResult.
 */
public class UserResult {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;

  /**
   * UserResult Constructor.
   */
  public UserResult(Long id, String email, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * UserResult.
   */
  public static UserResult fromUser(User user) {
    return new UserResult(user.getId(), user.getEmail(), user.getName(), user.getPhoneNumber());
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public String toString() {
    return "UserResult{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
