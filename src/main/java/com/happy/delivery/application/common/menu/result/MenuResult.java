package com.happy.delivery.application.common.menu.result;

import com.happy.delivery.domain.common.menu.Menu;

/**
 * MenuResult.
 */
public class MenuResult {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  /**
   * MenuResult fromMenu.
   */
  public static MenuResult fromMenu(Menu menu) {
    return new MenuResult(
        menu.getMenuId(),
        menu.getMenuName(),
        menu.getMenuDetail(),
        menu.getMenuPrice()
    );
  }

  /**
   * MenuResult Constructor.
   */
  public MenuResult(Long menuId, String menuName, String menuDetail, Long menuPrice) {
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
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

  public Long getMenuPrice() {
    return menuPrice;
  }

  @Override
  public String toString() {
    return "MenuResult{" +
        "menuId=" + menuId +
        ", menuName='" + menuName + '\'' +
        ", menuDetail='" + menuDetail + '\'' +
        ", menuPrice=" + menuPrice +
        '}';
  }
}
