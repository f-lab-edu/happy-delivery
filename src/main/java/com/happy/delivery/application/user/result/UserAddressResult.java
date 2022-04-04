package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.UserAddress;

/**
 * UserAddressResult.
 */
public class UserAddressResult {

  private Long id;
  private Long userId;
  private String addressDetail;

  /**
   * UserAddressResult constructor.
   */
  public UserAddressResult(Long id, Long userId, String addressDetail) {
    this.id = id;
    this.userId = userId;
    this.addressDetail = addressDetail;
  }

  /**
   * UserAddressResult.
   */
  public static UserAddressResult fromUserAddress(UserAddress userAddress) {
    return new UserAddressResult(
        userAddress.getId(),
        userAddress.getUserId(),
        userAddress.getAddressDetail());
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "UserAddressResult{" +
        "id=" + id +
        ", userId=" + userId +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
