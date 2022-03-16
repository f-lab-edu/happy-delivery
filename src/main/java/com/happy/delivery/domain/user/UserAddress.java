package com.happy.delivery.domain.user;

import org.springframework.lang.Nullable;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private Long userId;
  private String addressCode;
  @Nullable
  private String addressDetail;

  public UserAddress() {
  }

  /**
   * UserAddress savePassword Constructor.
   */
  public UserAddress(Long userId, String addressCode, String addressDetail) {
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  /**
   * changeAddress().
   * 주소 변경.
   */
  public void changeAddress(String addressCode, String addressDetail) {
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public String getAddressCode() {
    return addressCode;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "UserAddress{" +
        "id=" + id +
        ", userId=" + userId +
        ", addressCode='" + addressCode + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
