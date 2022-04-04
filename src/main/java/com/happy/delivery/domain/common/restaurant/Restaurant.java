package com.happy.delivery.domain.common.restaurant;

import com.happy.delivery.infra.vo.AddressObject;

/**
 * Restaurant.
 */
public class Restaurant {

  private Long id;
  private String name;
  private String category;
  private AddressObject address;
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
    this.address = AddressObject.of(longitude, latitude);
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
    return this.address.getLongitude();
  }

  public Double getLatitude() {
    return this.address.getLatitude();
  }

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", address=" + address +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
