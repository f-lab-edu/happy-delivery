package com.happy.delivery.application.user.command;

import org.springframework.lang.Nullable;

/**
 * AddressCommand.
 */
public class AddressCommand {

  private Long addressId;
  private final Long userId;
  private Double longitude;
  private Double latitude;
  @Nullable
  private final String addressDetail;

  /**
   * AddressCommand Constructor.
   */
  public AddressCommand(Long addressId, Long userId, Double longitude, Double latitude,
      @Nullable String addressDetail) {
    this.addressId = addressId;
    this.userId = userId;
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
  }

  public Long getAddressId() {
    return addressId;
  }

  public Long getUserId() {
    return userId;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  @Nullable
  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "AddressCommand{" +
        "addressId=" + addressId +
        ", userId=" + userId +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
