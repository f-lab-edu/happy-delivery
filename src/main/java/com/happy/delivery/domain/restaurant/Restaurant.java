package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.vo.PointValue;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
