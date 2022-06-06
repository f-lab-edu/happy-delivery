package com.happy.delivery.domain.restaurant.vo;

import java.util.Objects;

/**
 * RestaurantSearchValue.
 * 고객의 위치와 근처라고 정의한 거리를 받아오는 Object.
 * RestaurantSearchRequest -> RestaurantSearchCommand -> RestaurantSearchValue.
 * DB에 저장되는 entity 가 아님.
 */
public class RestaurantSearchValue {

  private Double longitude;
  private Double latitude;
  private Integer distance;

  private RestaurantSearchValue() {
  }

  private RestaurantSearchValue(Double longitude, Double latitude, Integer distance) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.distance = distance;
  }

  public static RestaurantSearchValue of(Double longitude, Double latitude, Integer distance) {
    return new RestaurantSearchValue(longitude, latitude, distance);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantSearchValue that = (RestaurantSearchValue) o;
    return Objects.equals(longitude, that.longitude) && Objects.equals(latitude,
        that.latitude) && Objects.equals(distance, that.distance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude, distance);
  }
}
