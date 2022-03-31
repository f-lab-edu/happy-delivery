package com.happy.delivery.infra.vo;

import java.awt.Point;
import java.util.Objects;

/**
 * Location.
 */
public class Location {

  private Double longitude;
  private Double latitude;
  private String location;

  /**
   * Location constructor.
   */
  public Location(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.location = String.format("POINT(%s %s)", longitude, latitude);
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getLocation() {
    return location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location1 = (Location) o;
    return longitude.equals(location1.longitude) && latitude.equals(location1.latitude) &&
        Objects.equals(location, location1.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude, location);
  }

  @Override
  public String toString() {
    return "RestaurantLocationVO{" +
        "longitude=" + longitude +
        ", latitude=" + latitude +
        '}';
  }
}
