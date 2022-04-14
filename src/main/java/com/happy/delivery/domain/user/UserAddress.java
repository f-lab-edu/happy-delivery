package com.happy.delivery.domain.user;

import com.happy.delivery.infra.vo.AddressObject;

/**
 * UserAddress.
 */
public class UserAddress {

  private Long id;
  private Long userId;
  private AddressObject address;
  private String addressDetail;
  private Boolean mainAddress;

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
  public UserAddress(Long userId, Double longitude, Double latitude, String addressDetail,
      Boolean mainAddress) {
    this.userId = userId;
    this.address = AddressObject.of(longitude, latitude);
    this.addressDetail = addressDetail;
    this.mainAddress = mainAddress;
  }

  /**
   * changeAddress(). 주소 변경.
   */
  public void changeAddress(Double longitude, Double latitude, String addressDetail) {
    this.address = AddressObject.of(longitude, latitude);
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

  public String getAddress() {
    return this.address.toString();
  }

  /**
   * setAddress.
   * DB에서 POINT값을 ST_ASTEXT를 이용해 String으로 바꿔서 보내주면,
   * Mybatis가 UserAddress의 setAddress를 이용해서 객체에 값을 주입시켜줌.
   * String address 형태 : POINT(127.04298707366922 37.512764805693074)
   */
  public void setAddress(String address) {
    this.address = AddressObject.of(address);
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  public Boolean getMainAddress() {
    return mainAddress;
  }

  public void setMainAddress(Boolean mainAddress) {
    this.mainAddress = mainAddress;
  }
}
