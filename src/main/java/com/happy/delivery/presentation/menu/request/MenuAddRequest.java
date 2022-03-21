package com.happy.delivery.presentation.menu.request;

import com.happy.delivery.application.menu.command.MenuAddCommand;
import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.SignupCommand;

public class MenuAddRequest {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  public MenuAddRequest(Long menuId, String menuName, String menuDetail, Long menuPrice) {
    this.menuId = menuId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
  }

  public MenuAddCommand toCommand() {
    MenuAddCommand menuAddCommand = new MenuAddCommand(
        this.menuId,
        this.menuName,
        this.menuDetail,
        this.menuPrice
    );
    return menuAddCommand;
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
