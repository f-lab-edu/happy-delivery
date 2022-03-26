package com.happy.delivery.domain.user;

/**
 * Menu.
 */
public class Menu {
  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  /**
   * Menu Constructor.
   */
  public Menu(Long menuId, String menuName, String menuDetail, Long menuPrice) {
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
    return "Menu{" +
        "menuId=" + menuId +
        ", menuName='" + menuName + '\'' +
        ", menuDetail='" + menuDetail + '\'' +
        ", menuPrice=" + menuPrice +
        '}';
  }
}
