package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.OptionGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuResult.
 */
public class MenuResult {

  private final Long menuId;
  private final Long menuGroupId;
  private final String menuName;
  private final String menuDetail;
  private final Integer menuPrice;
  private List<OptionGroupResult> optionGroupResultList = new ArrayList<>();

  /**
   * MenuResult Constructor.
   */
  public MenuResult(Long menuId, Long menuGroupId, String menuName, String menuDetail,
      Integer menuPrice, List<OptionGroupResult> optionGroupResultList) {
    this.menuId = menuId;
    this.menuGroupId = menuGroupId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
    this.optionGroupResultList = optionGroupResultList;
  }

  /**
   * MenuResult fromMenu.
   */
  public static MenuResult fromMenu(Menu menu) {
    return new MenuResult(
        menu.getMenuId(),
        menu.getMenuGroup().getId(),
        menu.getMenuName(),
        menu.getMenuDetail(),
        menu.getMenuPrice(),
        changeOptionGroupListToOptionGroupResultList(menu.getOptionGroupList()));
  }

  /**
   * changeOptionGroupListToOptionGroupResultList.
   * 리스트 변형 : OptionGroup => OptionGroupResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static List<OptionGroupResult> changeOptionGroupListToOptionGroupResultList(
      List<OptionGroup> optionGroups) {
    List<OptionGroupResult> results = new ArrayList<>();
    for (OptionGroup optionGroup : optionGroups) {
      results.add(OptionGroupResult.fromOptionGroup(optionGroup));
    }
    return results;
  }

  public Long getMenuId() {
    return menuId;
  }

  public Long getMenuGroupId() {
    return menuGroupId;
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

  public List<OptionGroupResult> getOptionGroupResultList() {
    return optionGroupResultList;
  }
}
