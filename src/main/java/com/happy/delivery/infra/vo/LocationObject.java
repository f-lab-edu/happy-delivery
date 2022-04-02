package com.happy.delivery.infra.vo;

import com.happy.delivery.domain.exception.user.UserLocationNullPointException;
import java.util.Objects;

/**
 * Location.
 */
public final class LocationObject {

  private final Double longitude;
  private final Double latitude;

  /**
   * Location constructor.
   */
  private LocationObject(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    validateLongitudeAndLatitude();
  }

  public static LocationObject of(Double longitude, Double latitude) {
    return new LocationObject(longitude, latitude);
  }

  private void validateLongitudeAndLatitude() {
    if (this.longitude == null || this.latitude == null) {
      throw new UserLocationNullPointException("경도 위도를 모두 작성해주세요.");
    }
  }

  /**
   * toString.
   * 내부 데이터를 추출하여 다른 유형으로 변환하기.
   */
  public String toString() {
    //"point(" + longitude + " " + latitude + ")"
    return String.format("point(%s %s)", longitude, latitude);
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationObject that = (LocationObject) o;
    return longitude.equals(that.longitude) && latitude.equals(that.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }
}
