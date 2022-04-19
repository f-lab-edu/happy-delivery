package com.happy.delivery.domain.vo;

import com.happy.delivery.domain.exception.user.LatitudeOutOfBoundsException;
import com.happy.delivery.domain.exception.user.LongitudeOrLatitudeNullPointException;
import com.happy.delivery.domain.exception.user.LongitudeOutOfBoundsException;
import com.happy.delivery.domain.exception.user.PointWktReaderParseException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

/**
 * Address.
 */
@Embeddable
public final class Address {

  @Column
  private Double longitude;

  @Column
  private Double latitude;

  @Column
  private Point address;

  /**
   * Address constructor.
   * 기본 생성자(Default Constructor)가 필요한 이유는
   * JPA는 조회시 default constructor로 객체를 생성한 뒤 Reflection을 이용해 값을 매핑하기 때문이다.
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
    this.address = pointOf(longitude, latitude);
  }

  public static Address of(Double longitude, Double latitude) {
    return new Address(longitude, latitude);
  }

  /**
   * UserAddress setAddress()를 위해서 만듦.
   * String address를 받아 파싱한 후에 longitude, latitude에 넣어줌.
   */
  public static Address of(String address) {
    String strLongitude = address.substring(address.indexOf('(') + 1, address.indexOf(' '));
    String strLatitude = address.substring(address.indexOf(' ') + 1, address.indexOf(')'));
    return new Address(Double.parseDouble(strLongitude), Double.parseDouble(strLatitude));
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

  /**
   * pointOf.
   * 경위도 값을 받아서 String 타입으로 변환한 뒤 다시 point 타입으로 만드는 메서드.
   */
  public Point pointOf(Double longitude, Double latitude) {
    String pointWkt = String.format("POINT(%s %s)", longitude, latitude);
    try {
      return (Point) new WKTReader().read(pointWkt);
    } catch (ParseException ex) {
      throw new PointWktReaderParseException();
    }
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  /**
   * getPointString.
   * MyBatis를 위해서 만든 메서드.
   * point값을 String 타입으로 반환.
   */
  public String getPointAsStringType() {
    return String.format("POINT(%s %s)", longitude, latitude);
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
