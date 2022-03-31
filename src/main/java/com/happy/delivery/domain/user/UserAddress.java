package com.happy.delivery.domain.user;

import org.springframework.lang.Nullable;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private Long userId;
  private Double longitude;
  private Double latitude;
  private String location;
  @Nullable
  private String addressDetail;

  public UserAddress() {
  }

  /**
   * UserAddress saveAddress Constructor.
   */
  public UserAddress(Long userId, Double longitude, Double latitude, String addressDetail) {
    this.userId = userId;
    this.longitude = longitude;
    this.latitude = latitude;
    setLocation(longitude, latitude);
    this.addressDetail = addressDetail;
  }

  /**
   * changeAddress().
   * 주소 변경.
   */
  public void changeAddress(Double longitude, Double latitude, String addressDetail) {
    this.longitude = longitude;
    this.latitude = latitude;
    setLocation(longitude, latitude);
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

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(Double longitude, Double latitude) {
    this.location = "point(" + longitude + " " + latitude + ")";
  }

  @Override
  public String toString() {
    return "UserAddress{" +
        "id=" + id +
        ", userId=" + userId +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", location='" + location + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
