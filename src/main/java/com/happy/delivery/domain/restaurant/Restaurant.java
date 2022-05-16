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

  /*
      양방향 연관관계 주인이 아닌 쪽 :
            값을 읽어오는 것만 가능하다. (read only)
            mappedBy 속성을 이용해 주인과 연결해줘야 한다.
            mappedBy 값으로는 주인 이름을 값으로 넣어줘야 한다.
            => 지금은 MenuGroup.restaurant 가 주인이므로 해당 변수의 이름인 "restaurant" 가 값으로 들어간다.
  */
  @OneToMany(mappedBy = "restaurant")
  List<MenuGroup> menuGroupList = new ArrayList<>();

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

  public List<MenuGroup> getMenuGroupList() {
    return menuGroupList;
  }
}
