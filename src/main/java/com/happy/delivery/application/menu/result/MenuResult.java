package com.happy.delivery.application.menu.result;

public class MenuResult {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  public MenuResult(Long menuId, String menuName, String menuDetail, Long menuPrice) {
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
  }
}
