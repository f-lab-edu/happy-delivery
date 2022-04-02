package com.happy.delivery.domain.common.restaurant;

import com.happy.delivery.infra.vo.LocationObject;

/**
 * RestaurantLocation.
 */
public class RestaurantLocation {

  private final LocationObject location;

  /**
   * RestaurantLocation Constructor.
   */
  public RestaurantLocation(Double longitude, Double latitude) {
    this.location = LocationObject.of(longitude, latitude);
  }

  public Double getLongitude() {
    return this.location.getLongitude();
  }

  public Double getLatitude() {
    return this.location.getLatitude();
  }


}
