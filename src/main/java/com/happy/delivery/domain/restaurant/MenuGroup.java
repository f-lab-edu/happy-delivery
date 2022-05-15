package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.MenuGroupId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * MenuGroups.
 * 메뉴들의 묶음.
 * Ex) 특선메뉴, 인기메뉴, 기본메뉴 등.
 */
@Entity(name = "menu_groups")
@IdClass(MenuGroupId.class)
public class MenuGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_group_id")
  private Long id;

  // JoinColumn 는 외래키를 갖는 칼럼의 이름입니다.
  @Id
  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Column
  private String name;

  /**
   * MenuGroups default constructor.
   */
  public MenuGroup() {
  }

  /**
   * MenuGroups Args constructor.
   */
  public MenuGroup(Long id, Restaurant restaurant, String name) {
    this.id = id;
    this.restaurant = restaurant;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public String getName() {
    return name;
  }
}
