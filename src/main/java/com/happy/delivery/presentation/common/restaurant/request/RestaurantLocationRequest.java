package com.happy.delivery.presentation.common.restaurant.request;

import com.happy.delivery.application.common.restaurant.command.RestaurantLocationCommand;

/**
 * RestaurantLocationRequest.
 */
public class RestaurantLocationRequest {

  private final Double longitude;
  private final Double latitude;

  public RestaurantLocationRequest(Double longitude, Double latitude) {
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
   * RestaurantLocationCommand.
   * request -> command.
   */
  public RestaurantLocationCommand toCommand() {
    return new RestaurantLocationCommand(
        this.longitude,
        this.latitude);
  }
}
