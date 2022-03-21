package com.happy.delivery.application.menu.command;

public class MenuAddCommand {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  public MenuAddCommand(Long menuId, String menuName, String menuDetail, Long menuPrice) {
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
}
