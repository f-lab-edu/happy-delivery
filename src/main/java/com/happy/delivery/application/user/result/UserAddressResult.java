package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.UserAddress;

/**
 * UserAddressResult.
 */
public class UserAddressResult {

  private Long id;
  private Long userId;
  private String addressDetail;
  private Boolean mainAddress;

  /**
   * UserAddressResult constructor.
   */
  public UserAddressResult(Long id, Long userId, String addressDetail, Boolean mainAddress) {
    this.id = id;
    this.userId = userId;
    this.addressDetail = addressDetail;
    this.mainAddress = mainAddress;
  }

  /**
   * UserAddressResult.
   */
  public static UserAddressResult fromUserAddress(UserAddress userAddress) {
    return new UserAddressResult(
        userAddress.getId(),
        userAddress.getUserId(),
        userAddress.getAddressDetail(),
        userAddress.getMainAddress());
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

  public Boolean getMainAddress() {
    return mainAddress;
  }

  @Override
  public String toString() {
    return "UserAddressResult{" +
        "id=" + id +
        ", userId=" + userId +
        ", addressDetail='" + addressDetail + '\'' +
        ", mainAddress=" + mainAddress +
        '}';
  }
}
