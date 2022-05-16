package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.MenuId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_id")
  private Long menuId;

  @Id
  @ManyToOne
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

  /*
     양방향 연관관계 주인이 아닌 쪽 :
           값을 읽어오는 것만 가능하다. (read only)
           mappedBy 속성을 이용해 주인과 연결해줘야 한다.
           mappedBy 값으로는 주인 이름을 값으로 넣어줘야 한다.
           => 지금은 MenuGroup.menu 이 주인이므로 해당 변수의 이름인 "menu" 가 값으로 들어간다.
 */
  @OneToMany(mappedBy = "menu")
  List<OptionGroup> optionGroupList = new ArrayList<>();

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

  public List<OptionGroup> getOptionGroupList() {
    return optionGroupList;
  }
}
