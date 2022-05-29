package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.vo.PointValue;
import java.util.Set;
import java.util.TreeSet;
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

      List 가 아닌 Set 을 사용하는 이유 :
            1개의 entity 를 가져올 때 2개 이상의 bag 이 있을 경우 MultipleBagFetchException 이 발생한다.
            bag 은 hibernate 에서 사용하는 collection 으로, list 처럼 중복을 허용하지만 set 처럼 순서가 없다.
            이 bag 은 fetch join 을 위해서 사용하고, fetch-join 은 in-memory 에서 처리한다.
            즉, fetch-join 을 하면 DB 에서 모든 값들을 가져와서 in-memory 에 저장하고, 애플리케이션 단에서 필요한 값만큼 정제해 사용한다.
            따라서 collection join 이 2개 이상이라면 너무 많은 값들이 메모리로 들어와 exception 을 발생한다.
            bag 의 값을 set 으로 받아, 처음부터 중복을 허여하지 않는다면 이러한 오류를 해결할 수 있다.

      linkedHashSet 이 아닌 TreeSet 을 사용하는 이유 :
            linkedHashSet 은 입력된 순서를 보장할 뿐, 내가 순서대로 값을 정렬해주지는 않는다.
            TreeSet 은 Comparable 을 사용하여 원하는대로 값들을 정렬할 수 있다.
 */
  @OneToMany(mappedBy = "restaurant")
  Set<MenuGroup> menuGroupSet = new TreeSet<>();

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

  public Set<MenuGroup> getMenuGroupSet() {
    return menuGroupSet;
  }
}
