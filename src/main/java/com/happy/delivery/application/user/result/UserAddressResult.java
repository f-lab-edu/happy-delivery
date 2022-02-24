package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.UserAddress;
import java.time.Instant;

/**
 * UserAddressResult.
 */
public class UserAddressResult {

  private Long id;
  private Long userId;
  private String addressCode;
  private String addressDetail;
  private Instant date;

  /**
   * UserAddressResult constructor.
   */
  public UserAddressResult(Long id, Long userId, String addressCode, String addressDetail,
      Instant date) {
    this.id = id;
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
    this.date = date;
  }

  /**
   * UserAddressResult.
   */
  public static UserAddressResult fromUserAddress(UserAddress userAddress) {
    return new UserAddressResult(
        userAddress.getId(),
        userAddress.getUserId(),
        userAddress.getAddressCode(),
        userAddress.getAddressDetail(),
        userAddress.getDate());
  }

  public Long getId() {
    return id;
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

  public Instant getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "UserAddressResult{" +
        "id=" + id +
        ", userId=" + userId +
        ", addressCode='" + addressCode + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        ", date=" + date +
        '}';
  }
}
