package com.happy.delivery.domain.restaurant;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * MenuGroups.
 * 메뉴들의 묶음.
 * Ex) 특선메뉴, 인기메뉴, 기본메뉴 등.
 */
@Entity(name = "menu_groups")
public class MenuGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_group_id")
  private Long id;

  // JoinColumn 는 외래키를 갖는 칼럼의 이름입니다.
  //  @ManyToOne 가 insert, update 등을 실행하기 때문에 owner 가 됩니다.
  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Column
  private String name;

  @OneToMany(mappedBy = "menuGroup")
  private List<Menu> menus = new ArrayList<>();

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

  @Override
  public String toString() {
    return "MenuGroups{" +
        "id=" + id +
        ", restaurant=" + restaurant +
        ", name='" + name + '\'' +
        '}';
  }
}
