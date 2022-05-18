package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.MenuGroupId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

  /*
        JoinColumn : 외래키를 갖는 칼럼을 지정한다.

        양방향 연관관계 주인 : 연관관계의 주인만이 외래키를 관리(등록, 수정)할 수 있다.
                          주인은 mappedBy를 사용할 수 없다.
                          양방향 매핑시 연관관계의 주인에 값을 입력해야 한다.
  */
  @Id
  @ManyToOne
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Column
  private String name;

  /*
      양방향 연관관계 주인이 아닌 쪽 :
            값을 읽어오는 것만 가능하다. (read only)
            mappedBy 속성을 이용해 주인과 연결해줘야 한다.
            mappedBy 값으로는 주인 이름을 값으로 넣어줘야 한다.
            => 지금은 Menu.menuGroup 이 주인이므로 해당 변수의 이름인 "menuGroup" 가 값으로 들어간다.
  */
  @OneToMany(mappedBy = "menuGroup")
  List<Menu> menuList = new ArrayList<>();

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

  public List<Menu> getMenuList() {
    return menuList;
  }
}
