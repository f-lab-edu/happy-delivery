package com.happy.delivery.infra.vo;

import com.happy.delivery.domain.exception.user.LatitudeOutOfBoundsException;
import com.happy.delivery.domain.exception.user.LongitudeOrLatitudeNullPointException;
import com.happy.delivery.domain.exception.user.LongitudeOutOfBoundsException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address.
 */
@Embeddable
public final class Address {

  @Column
  private Double longitude;

  @Column
  private Double latitude;

  /**
   * Address default constructor.
   * JPA를 위해 만들어 둔 생성자.
   */
  public Address() {
  }

  /**
   * Address constructor.
   */
  private Address(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    validateLongitudeAndLatitude();
  }

  public static Address of(Double longitude, Double latitude) {
    return new Address(longitude, latitude);
  }

  /**
   * UserAddress setAddress()를 위해서 만듦.
   * String address를 받아 파싱한 후에 longitude, latitude에 넣어줌.
   */
  public static Address of(String address) {
    String strLongitude = address.substring(0, address.indexOf(' '));
    String strLatitude = address.substring(address.indexOf(' ') + 1);
    return new Address(Double.parseDouble(strLongitude), Double.parseDouble(strLatitude));
  }

  private void validateLongitudeAndLatitude() {
    if (this.longitude == null || this.latitude == null) {
      throw new LongitudeOrLatitudeNullPointException("경도 위도를 모두 작성해주세요.");
    }

    if (this.longitude > -180.0 && this.longitude < 180.0) {
      throw new LongitudeOutOfBoundsException("경도가 범위를 벗어났습니다.");
    }

    if (this.latitude > -80.0 && this.latitude < 80.0) {
      throw new LatitudeOutOfBoundsException("위도가 범위를 벗어났습니다.");
    }
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
    Address that = (Address) o;
    return longitude.equals(that.longitude) && latitude.equals(that.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }
}
