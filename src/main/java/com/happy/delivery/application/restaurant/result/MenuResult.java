package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.MenuGroup;

/**
 * MenuResult.
 */
public class MenuResult {

  private Long menuId;

  private MenuGroup menuGroup;

  private String menuName;

  private String menuDetail;

  private Integer menuPrice;

  /**
   * MenuResult Constructor.
   */
  public MenuResult(Long menuId, MenuGroup menuGroup, String menuName, String menuDetail,
      Integer menuPrice) {
    this.menuId = menuId;
    this.menuGroup = menuGroup;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
  }

  /**
   * MenuResult fromMenu.
   */
  public static MenuResult fromMenu(Menu menu) {
    return new MenuResult(
        menu.getMenuId(),
        menu.getMenuGroup(),
        menu.getMenuName(),
        menu.getMenuDetail(),
        menu.getMenuPrice()
    );
  }

  public Long getMenuId() {
    return menuId;
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

}
