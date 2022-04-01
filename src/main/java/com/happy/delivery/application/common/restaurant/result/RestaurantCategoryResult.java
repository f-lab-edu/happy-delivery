package com.happy.delivery.application.common.restaurant.result;

import com.happy.delivery.domain.common.restaurant.RestaurantCategory;

/**
 * RestaurantCategoryResult.
 */
public class RestaurantCategoryResult {
  private int id;
  private String name;

  /**
   * RestaurantCategoryResult constructor.
   */
  public RestaurantCategoryResult(int id, String name) {
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
    return "RestaurantCategoryResult{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  /**
   * RestaurantCategoryResult.
   * RestaurantCategory -> RestaurantCategoryResult.
   */
  public static RestaurantCategoryResult fromRestaurantCategory(RestaurantCategory category) {
    return new RestaurantCategoryResult(
        category.getId(),
        category.getName()
    );
  }
}
