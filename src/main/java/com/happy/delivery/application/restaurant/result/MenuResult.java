package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.OptionGroup;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * MenuResult.
 */
public class MenuResult implements Comparable<MenuResult> {

  private final Long menuId;
  private final Long menuGroupId;
  private final String menuName;
  private final String menuDetail;
  private final Integer menuPrice;
  private Set<OptionGroupResult> optionGroups;

  /**
   * MenuResult Constructor.
   */
  public MenuResult(Long menuId, Long menuGroupId, String menuName, String menuDetail,
      Integer menuPrice, Set<OptionGroupResult> optionGroups) {
    this.menuId = menuId;
    this.menuGroupId = menuGroupId;
    this.menuName = menuName;
    this.menuDetail = menuDetail;
    this.menuPrice = menuPrice;
    this.optionGroups = optionGroups;
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
        toOptionGroupResultSet(menu.getOptionGroupSet()));
  }

  /**
   * toOptionGroupResultSet.
   * set 변형 : OptionGroup => OptionGroupResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static Set<OptionGroupResult> toOptionGroupResultSet(Set<OptionGroup> optionGroups) {
    Iterator<OptionGroup> itr = optionGroups.iterator();
    Set<OptionGroupResult> results = new TreeSet<>();
    while (itr.hasNext()) {
      results.add(OptionGroupResult.fromOptionGroup(itr.next()));
    }
    return results;
  }

  /**
   * compareTo.
   * MenuGroupResult 의 TreeSet 을 위한 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(MenuResult o) {
    Long value = this.menuId - o.getMenuId();
    return value.intValue();
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

  public Set<OptionGroupResult> getOptionGroups() {
    return optionGroups;
  }
}
