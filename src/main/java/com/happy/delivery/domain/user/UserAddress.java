package com.happy.delivery.domain.user;

import java.time.Instant;
import org.springframework.lang.Nullable;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private final Long userId;
  private String addressCode;
  @Nullable
  private String addressDetail;
  private Instant date;

  /**
   * UserAddress savePassword Constructor.
   */
  public UserAddress(Long userId, String addressCode, String addressDetail) {
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  /**
   * User not savePassword Constructor.
   */
  public UserAddress(Long id, Long userId, String addressCode, String addressDetail, Instant date) {
    this.id = id;
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
    this.date = date;
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

  public Instant getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "UserAddress{" +
        "id=" + id +
        ", userId=" + userId +
        ", addressCode='" + addressCode + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        ", date=" + date +
        '}';
  }
}
