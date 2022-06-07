package com.happy.delivery.presentation.restaurant.request;


import com.happy.delivery.application.restaurant.command.RestaurantSearchCommand;

/**
 * RestaurantSearchRequest.
 */
public class RestaurantSearchRequest {

  private Double longitude;
  private Double latitude;
  private Integer distance;

  /**
   * RestaurantSearchRequest Constructor.
   */
  public RestaurantSearchRequest(Double longitude, Double latitude, Integer distance) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.distance = distance;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Integer getDistance() {
    return distance;
  }

  /**
   * RestaurantSearchCommand.
   * request -> command.
   */
  public RestaurantSearchCommand toCommand() {
    return new RestaurantSearchCommand(
        this.longitude,
        this.latitude,
        this.distance
    );
  }
}
