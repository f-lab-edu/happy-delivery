package com.happy.delivery.domain.user;

import com.happy.delivery.infra.vo.LocationObject;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private Long userId;
  private LocationObject location;
  private String addressDetail;

  /**
   * UserAddress MyBatis Constructor.
   * Mybatis가 미리 result값을 넣엉줄 인스턴스를 생성한다.
   * 하지만 UserAddress 엔티티에는 모든 인자가 포함된 생성자(Builder)만이 존재하기 때문에,
   * 인스턴스를 생성할 수 없어 문제가 발생한다.
   * 이것을 해결하기 위해서는 인자가 없는 생성자를 추가하면 된다.
   */
  public UserAddress() {
  }

  /**
   * UserAddress saveAddress Constructor.
   */
  public UserAddress(Long userId, Double longitude, Double latitude, String addressDetail) {
    this.userId = userId;
    this.location = LocationObject.of(longitude, latitude);
    this.addressDetail = addressDetail;
  }

  /**
   * changeAddress().
   * 주소 변경.
   */
  public void changeAddress(Double longitude, Double latitude, String addressDetail) {
    this.location = LocationObject.of(longitude, latitude);
    this.addressDetail = addressDetail;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public String getLocation() {
    return this.location.toString();
  }

  public String getAddressDetail() {
    return addressDetail;
  }
}
