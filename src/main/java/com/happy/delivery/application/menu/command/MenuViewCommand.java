package com.happy.delivery.application.menu.command;

/**
 * MenuViewCommand.
 * layer에 맞는 dto 이름 사용, command partten을 많이 사용해서,
 * 각 계층별 api가 사용하는 dto가 다르기 때문에 dto를 분리.
 */
public class MenuViewCommand {

  private Long menuId;

  private String menuName;

  private String menuDetail;

  private Long menuPrice;

  /**
   * MenuViewCommand Constuctor.
   */
  public MenuViewCommand(Long menuId, String menuName, String menuDetail, Long menuPrice) {
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
