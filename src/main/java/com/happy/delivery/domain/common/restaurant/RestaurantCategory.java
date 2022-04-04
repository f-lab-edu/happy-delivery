package com.happy.delivery.domain.common.restaurant;

/**
 * RestaurantCategory.
 */
public class RestaurantCategory {

  private int id;
  private String name;

  /**
   * RestaurantCategory constructor.
   */
  public RestaurantCategory(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "RestaurantCategory{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
