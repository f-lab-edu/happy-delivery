package com.happy.delivery.application.common.restaurant.command;

import com.happy.delivery.domain.common.restaurant.Restaurant;

/**
 * RestaurantCommand.
 */
public class RestaurantCommand {

  private Long id;
  private String name;
  private String category;
  private Double longitude;
  private Double latitude;
  private String addressDetail;

  /**
   * RestaurantCommand Constructor.
   */
  public RestaurantCommand(Long id, String name, String category, Double longitude,
      Double latitude, String addressDetail) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.longitude = longitude;
    this.latitude = latitude;
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

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  public Restaurant toRestaurant() {
    return new Restaurant(
        this.id,
        this.name,
        this.category,
        this.longitude,
        this.latitude,
        this.addressDetail
    );
  }
}
