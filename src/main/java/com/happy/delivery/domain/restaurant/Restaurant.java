package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.vo.PointValue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Restaurant.
 */
@Entity(name = "restaurants")
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_id")
  private Long id;

  @Column
  private String name;

  @Column
  private String category;

  @Embedded
  private PointValue pointValue;

  @Column(name = "address_detail")
  private String addressDetail;

  // mappedBy 를 사용했을 때와 사용하지 않았을 때 비교해보기!!
  // mappedBy == readOnly
  // mappedBy의 값은 Owner side 에서 @JoinColum 으로 선언한 변수의 이름입니다.
  @OneToMany(mappedBy = "restaurant")
  private List<MenuGroup> menuGroups = new ArrayList<>();

  /**
   * Restaurant Constructor for MyBatis and JPA.
   */
  public Restaurant() {
  }

  /**
   * Restaurant Constructor.
   */
  public Restaurant(Long id, String name, String category, Double longitude, Double latitude,
      String addressDetail) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.pointValue = PointValue.of(longitude, latitude);
    this.addressDetail = addressDetail;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public Double getLongitude() {
    return this.pointValue.getLongitude();
  }

  public Double getLatitude() {
    return this.pointValue.getLatitude();
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", address=" + pointValue +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
