package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.MenuId;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Menu.
 * 메뉴의 이름, 설명, 가격 저장.
 * ex) 특선초밥, 계란1+연여2+장어2, 13000원.
 */
@Entity(name = "menus")
@IdClass(MenuId.class)
public class Menu implements Comparable<Menu> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_id")
  private Long menuId;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns({
      @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id"),
      @JoinColumn(name = "menu_group_id", referencedColumnName = "menu_group_id")
  })
  private MenuGroup menuGroup;

  @Column(name = "name")
  private String menuName;

  @Column(name = "detail")
  private String menuDetail;

  @Column(name = "price")
  private Integer menuPrice;

  @OneToMany(mappedBy = "menu")
  Set<OptionGroup> optionGroupSet = new TreeSet<>();

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

  /**
   * compareTo.
   * MenuGroup 의 TreeSet 을 정렬하는 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(Menu o) {
    Long value = this.menuId - o.getMenuId();
    return value.intValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Menu menu = (Menu) o;
    return Objects.equals(menuId, menu.menuId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(menuId);
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

  public Set<OptionGroup> getOptionGroupSet() {
    return optionGroupSet;
  }
}
