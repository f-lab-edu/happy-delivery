package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.MenuGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuGroupResult.
 */
public class MenuGroupResult {

  private final Long id;
  private final String name;
  private List<MenuResult> menuResultList = new ArrayList<>();

  /**
   * MenuGroupResult Constructor.
   */
  public MenuGroupResult(Long id, String name, List<MenuResult> menuResultList) {
    this.id = id;
    this.name = name;
    this.menuResultList = menuResultList;
  }

  /**
   * fromMenuGroup.
   * MenuGroup(Domain) => MenuGroupResult(application).
   * 계층 변경하기.
   */
  public static MenuGroupResult fromMenuGroup(MenuGroup menuGroup) {
    return new MenuGroupResult(
        menuGroup.getId(),
        menuGroup.getName(),
        changeMenuToMenuResult(menuGroup.getMenuList()));
  }

  /**
   * changeMenuToMenuResult.
   * 리스트 변형 : Menu => ListMenuResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static List<MenuResult> changeMenuToMenuResult(List<Menu> menus) {
    List<MenuResult> results = new ArrayList<>();
    for (Menu menu : menus) {
      results.add(MenuResult.fromMenu(menu));
    }
    return results;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<MenuResult> getMenuResultList() {
    return menuResultList;
  }
}
