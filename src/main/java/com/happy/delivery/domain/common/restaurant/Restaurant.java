package com.happy.delivery.domain.common.restaurant;

import com.happy.delivery.infra.vo.LocationObject;

/**
 * Restaurant.
 */
public class Restaurant {

  private Long id;
  private String name;
  private String category;
  private LocationObject location;
  private String addressDetail;

  /**
   * Restaurant Constructor for MyBatis.
   */
  public Restaurant() {
  }

  /**
   * Restaurant Constructor.
   */
  public Restaurant(Long id, String name, String category, Double longitude, Double latitude,
      String addressDetail) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.location = LocationObject.of(longitude, latitude);
    this.addressDetail = addressDetail;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  public Double getLongitude() {
    return this.location.getLongitude();
  }

  public Double getLatitude() {
    return this.location.getLatitude();
  }

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", location=" + location +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
