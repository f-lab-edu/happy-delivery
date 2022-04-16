package com.happy.delivery.application.user.command;

import org.springframework.lang.Nullable;

/**
 * AddressCommand.
 */
public class AddressCommand {

  private Double longitude;
  private Double latitude;
  @Nullable
  private final String addressDetail;

  /**
   * AddressCommand Constructor.
   */
  public AddressCommand(Double longitude, Double latitude, @Nullable String addressDetail) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
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
    return "AddressSaveCommand{" +
        "longitude=" + longitude +
        ", latitude=" + latitude +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
