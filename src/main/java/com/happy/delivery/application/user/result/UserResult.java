package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.User;

/**
 * UserResult.
 */
public class UserResult {

  private final Long id;
  private final String email;
  private final String name;
  private final String phoneNumber;
  private final Long addressId;

  /**
   * UserResult Constructor.
   */
  public UserResult(Long id, String email, String name, String phoneNumber, Long addressId) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.addressId = addressId;
  }

  /**
   * UserResult -> User.
   */
  public static UserResult fromUser(User user) {
    return new UserResult(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getPhoneNumber(),
        user.getAddressId()
    );
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

  public Long getAddressId() {
    return addressId;
  }

  @Override
  public String toString() {
    return "UserResult{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", addressId=" + addressId +
        '}';
  }
}
