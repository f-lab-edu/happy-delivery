package com.happy.delivery.presentation.common.restaurant.request;


import com.happy.delivery.application.common.restaurant.command.RestaurantCommand;

/**
 * RestaurantRequest.
 */
public class RestaurantRequest {

  private Long id;
  private String name;
  private String category;
  private Double longitude;
  private Double latitude;
  private String addressDetail;

  /**
   * RestaurantRequest Constructor.
   */
  public RestaurantRequest(Long id, String name, String category, Double longitude,
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

  /**
   * RestaurantLocationCommand.
   * request -> command.
   */
  public RestaurantCommand toCommand() {
    return new RestaurantCommand(
        this.id,
        this.name,
        this.category,
        this.longitude,
        this.latitude,
        this.addressDetail
    );
  }
}
