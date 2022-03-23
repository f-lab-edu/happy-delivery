package com.happy.delivery.domain.restaurant;

/**
 * RestaurantCategory.
 */
public class RestaurantCategory {

  private Long id;
  private String name;

  /**
   * RestaurantCategory default constructor.
   */
  public RestaurantCategory() {
  }

  /**
   * RestaurantCategory constructor.
   */
  public RestaurantCategory(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
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
