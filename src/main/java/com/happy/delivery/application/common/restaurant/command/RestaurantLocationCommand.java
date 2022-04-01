package com.happy.delivery.application.common.restaurant.command;

import com.happy.delivery.domain.common.restaurant.RestaurantLocation;

/**
 * RestaurantLocationCommand.
 */
public class RestaurantLocationCommand {

  private final Double longitude;
  private final Double latitude;

  public RestaurantLocationCommand(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public RestaurantLocation toRestaurantLocation() {
    return new RestaurantLocation(this.longitude, this.latitude);
  }
}
