package com.happy.delivery.presentation.common.restaurant.request;


import com.happy.delivery.application.common.restaurant.command.RestaurantSearchCommand;

/**
 * RestaurantSearchRequest.
 */
public class RestaurantSearchRequest {

  private Double longitude;
  private Double latitude;

  /**
   * RestaurantSearchRequest Constructor.
   */
  public RestaurantSearchRequest(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  /**
   * RestaurantSearchCommand.
   * request -> command.
   */
  public RestaurantSearchCommand toCommand() {
    return new RestaurantSearchCommand(
        this.longitude,
        this.latitude
    );
  }
}
