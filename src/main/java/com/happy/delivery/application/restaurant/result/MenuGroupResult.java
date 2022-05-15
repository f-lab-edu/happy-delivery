package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.Restaurant;

/**
 * MenuGroupResult.
 */
public class MenuGroupResult {

  private Long id;
  private Restaurant restaurant;
  private String name;

  /**
   * MenuGroupResult Constructor.
   */
  public MenuGroupResult(Long id, Restaurant restaurant, String name) {
    this.id = id;
    this.restaurant = restaurant;
    this.name = name;
  }

  /**
   * fromMenuGroup.
   * MenuGroup(Domain) => MenuGroupResult(application).
   * 계층 변경하기.
   */
  public static MenuGroupResult fromMenuGroup(MenuGroup menuGroup) {
    return new MenuGroupResult(
        menuGroup.getId(),
        menuGroup.getRestaurant(),
        menuGroup.getName());
  }

  public Long getId() {
    return id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public String getName() {
    return name;
  }


}
