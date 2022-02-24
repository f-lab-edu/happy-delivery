package com.happy.delivery.domain.user;

import java.time.Instant;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private final Long userId;
  private final String addressCode;
  private final String addressDetail;
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

}
