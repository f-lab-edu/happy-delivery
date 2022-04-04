package com.happy.delivery.application.user.command;

import com.happy.delivery.domain.common.restaurant.Restaurant;

/**
 * RestaurantCommand.
 */
public class RestaurantSearchCommand {

  private Double longitude;
  private Double latitude;

  /**
   * RestaurantSearchCommand Constructor.
   */
  public RestaurantSearchCommand(Double longitude, Double latitude) {
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
   * toRestaurant.
   * command -> entity.
   */
  public Restaurant toRestaurant() {
    return new Restaurant(
        null,
        null,
        null,
        this.longitude,
        this.latitude,
        null
    );
  }
}
