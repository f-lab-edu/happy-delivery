package com.happy.delivery.presentation.menu.request;

import com.happy.delivery.application.menu.command.MenuViewCommand;

/**
 * MenuViewRequest.
 */
public class MenuViewRequest {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  /**
   * MenuViewRequest Constructor.
   */
  public MenuViewRequest(Long menuId, String menuName, String menuDetail, Long menuPrice) {
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
  }

  /**
   * MenuViewCommand toCommand.
   */
  public MenuViewCommand toCommand() {
    MenuViewCommand menuViewCommand = new MenuViewCommand(
        this.menuId,
        this.menuName,
        this.menuDetail,
        this.menuPrice
    );
    return menuViewCommand;
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
