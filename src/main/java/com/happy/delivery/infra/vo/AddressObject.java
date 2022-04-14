package com.happy.delivery.infra.vo;

import com.happy.delivery.domain.exception.user.LongitudeOrLatitudeNullPointException;
import java.util.Objects;

/**
 * Location.
 */
public final class AddressObject {

  private final Double longitude;
  private final Double latitude;

  /**
   * Location constructor.
   */
  private AddressObject(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    validateLongitudeAndLatitude();
  }

  public static AddressObject of(Double longitude, Double latitude) {
    return new AddressObject(longitude, latitude);
  }

  /**
   * UserAddress setAddress()를 위해서 만듦.
   * String address를 받아
   * 파싱해서 longitude, latitude에 넣어주는 메서드.
   */
  public static AddressObject of(String address) {
    String strLongitude = address.substring(address.indexOf('(') + 1, address.indexOf(' '));
    String strLatitude = address.substring(address.indexOf(' ') + 1, address.indexOf(')'));
    return new AddressObject(Double.parseDouble(strLongitude), Double.parseDouble(strLatitude));
  }

  private void validateLongitudeAndLatitude() {
    if (this.longitude == null || this.latitude == null) {
      throw new LongitudeOrLatitudeNullPointException("경도 위도를 모두 작성해주세요.");
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
    AddressObject that = (AddressObject) o;
    return longitude.equals(that.longitude) && latitude.equals(that.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }
}
