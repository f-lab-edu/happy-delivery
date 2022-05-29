package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.MenuGroup;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * MenuGroupResult.
 */
public class MenuGroupResult implements Comparable<MenuGroupResult> {

  private final Long id;
  private final String name;
  private Set<MenuResult> menus;

  /**
   * MenuGroupResult Constructor.
   */
  public MenuGroupResult(Long id, String name, Set<MenuResult> menus) {
    this.id = id;
    this.name = name;
    this.menus = menus;
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
        toMenuResultSet(menuGroup.getMenuSet()));
  }

  /**
   * toMenuResultSet.
   * set 변형 : Menu => MenuResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static Set<MenuResult> toMenuResultSet(Set<Menu> menus) {
    Iterator<Menu> itr = menus.iterator();
    Set<MenuResult> results = new TreeSet<>();
    while (itr.hasNext()) {
      results.add(MenuResult.fromMenu(itr.next()));
    }
    return results;
  }

  /**
   * compareTo.
   * RestaurantResult 의 TreeSet 을 정렬하는 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(MenuGroupResult o) {
    Long value = this.id - o.getId();
    return value.intValue();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<MenuResult> getMenus() {
    return menus;
  }
}
