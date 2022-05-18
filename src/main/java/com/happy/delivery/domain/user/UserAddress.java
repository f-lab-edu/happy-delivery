package com.happy.delivery.domain.user;


import com.happy.delivery.domain.vo.PointValue;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserAddress.
 */
@Entity
@Table(name = "user_addresses")
public class UserAddress {

  @Id
  @Column(name = "user_address_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Embedded
  private PointValue pointValue;

  @Column(name = "address_detail")
  private String addressDetail;

  @Column(name = "main_address")
  private Boolean mainAddress;

  /**
   * UserAddress MyBatis Constructor.
   * Mybatis가 미리 result값을 넣어줄 인스턴스를 생성한다.
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
    this.pointValue = PointValue.of(longitude, latitude);
    this.addressDetail = addressDetail;
    this.mainAddress = mainAddress;
  }

  /**
   * changeAddress(). 주소 변경.
   */
  public void changeAddress(Double longitude, Double latitude, String addressDetail,
      Boolean mainAddress) {
    this.pointValue = PointValue.of(longitude, latitude);
    this.addressDetail = addressDetail;
    this.mainAddress = mainAddress;
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

  public String getAddressDetail() {
    return addressDetail;
  }

  public Boolean getMainAddress() {
    return mainAddress;
  }

  public void setMainAddress(Boolean mainAddress) {
    this.mainAddress = mainAddress;
  }

  public Double getLongitude() {
    return pointValue.getLongitude();
  }

  public Double getLatitude() {
    return pointValue.getLatitude();
  }
}
