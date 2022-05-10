package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.vo.Address;
import javax.persistence.Column;
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
  private Long id;

  @Column
  private String name;

  @Column
  private String category;
  //  @Column
  //  @ManyToOne
  //  private RestaurantCategory category;

  @Column
  private Address address;

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
    this.address = Address.of(longitude, latitude);
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

  public String getAddressDetail() {
    return addressDetail;
  }

  public Double getLongitude() {
    return this.address.getLongitude();
  }

  public Double getLatitude() {
    return this.address.getLatitude();
  }

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", address=" + address +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
