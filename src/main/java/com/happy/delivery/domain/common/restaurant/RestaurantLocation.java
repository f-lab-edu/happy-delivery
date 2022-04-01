package com.happy.delivery.domain.common.restaurant;

/**
 * RestaurantLocation.
 */
public class RestaurantLocation {

  private final Double longitude;
  private final Double latitude;

  public RestaurantLocation(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  @Override
  public String toString() {
    return "RestaurantLocation{" +
        "longitude=" + longitude +
        ", latitude=" + latitude +
        '}';
  }
}
