package com.happy.delivery.application.restaurant.command;

import com.happy.delivery.domain.restaurant.vo.RestaurantSearchValue;

/**
 * RestaurantCommand.
 */
public class RestaurantSearchCommand {

  private Double longitude;
  private Double latitude;
  private Integer distance;

  /**
   * RestaurantSearchCommand Constructor.
   */
  public RestaurantSearchCommand(Double longitude, Double latitude, Integer distance) {
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
   * toRestaurant.
   * command -> entity.
   */
  public RestaurantSearchValue toRestaurantSearchValue() {
    return RestaurantSearchValue.of(this.longitude, this.latitude, this.distance);
  }
}
