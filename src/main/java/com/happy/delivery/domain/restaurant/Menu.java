package com.happy.delivery.domain.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Menu.
 * 메뉴의 이름, 설명, 가격 저장.
 * ex) 특선초밥, 계란1+연여2+장어2, 13000원.
 */
@Entity(name = "menus")
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_id")
  private Long menuId;

  @ManyToOne
  @JoinColumns(value = {@JoinColumn(name = "menu_group_id", referencedColumnName = "menu_group_id"),
      @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")})
  private MenuGroup menuGroup;

  @Column(name = "name")
  private String menuName;

  @Column(name = "detail")
  private String menuDetail;

  @Column(name = "price")
  private Integer menuPrice;

  /**
   * Menu Constructor.
   */
  public Menu() {
  }

  /**
   * Menu Ars Constructor.
   */
  public Menu(Long menuId, MenuGroup menuGroup, String menuName, String menuDetail,
      Integer menuPrice) {
    this.menuId = menuId;
    this.menuGroup = menuGroup;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
  }

  public Long getMenuId() {
    return menuId;
  }

  public MenuGroup getMenuGroup() {
    return menuGroup;
  }

  public String getMenuName() {
    return menuName;
  }

  public String getMenuDetail() {
    return menuDetail;
  }

  public Integer getMenuPrice() {
    return menuPrice;
  }

  @Override
  public String toString() {
    return "Menu{" +
        "menuId=" + menuId +
        ", menuGroup=" + menuGroup +
        ", menuName='" + menuName + '\'' +
        ", menuDetail='" + menuDetail + '\'' +
        ", menuPrice=" + menuPrice +
        '}';
  }
}
