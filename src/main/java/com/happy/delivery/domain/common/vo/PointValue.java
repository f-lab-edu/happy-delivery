package com.happy.delivery.domain.common.vo;

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
public final class PointValue {

  @Column
  private Double longitude;

  @Column
  private Double latitude;

  /**
   * PointValue constructor.
   * 기본 생성자(Default Constructor)가 필요한 이유는
   * JPA는 조회시 default constructor로 객체를 생성한 뒤 Reflection을 이용해 값을 매핑하기 때문이다.
   */
  protected PointValue() {
  }

  /**
   * PointValue constructor.
   */
  private PointValue(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
    validateLongitudeAndLatitude();
  }

  public static PointValue of(Double longitude, Double latitude) {
    return new PointValue(longitude, latitude);
  }


  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }


  private void validateLongitudeAndLatitude() {
    if (longitude == null || latitude == null) {
      throw new LongitudeOrLatitudeNullPointException("경도 위도를 모두 작성해주세요.");
    }

    if ((longitude.compareTo(-180.0) < 0) && (longitude.compareTo(180.0) > 0)) {
      throw new LongitudeOutOfBoundsException("경도가 범위를 벗어났습니다.");
    }

    if (longitude.compareTo(-80.0) < 0  && longitude.compareTo(80.0) > 0) {
      throw new LatitudeOutOfBoundsException("위도가 범위를 벗어났습니다.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointValue pointValue = (PointValue) o;
    return longitude.equals(pointValue.longitude) && latitude.equals(pointValue.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }
}
