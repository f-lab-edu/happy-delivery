package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.RestaurantCategory;

/**
 * RestaurantCategoryResult.
 */
public class RestaurantCategoryResult {
  private String name;

  /**
   * RestaurantCategoryResult constructor.
   */
  public RestaurantCategoryResult(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "RestaurantCategoryResult{" +
        "name='" + name + '\'' +
        '}';
  }

  /**
   * RestaurantCategoryResult.
   * RestaurantCategory -> RestaurantCategoryResult.
   */
  public static RestaurantCategoryResult fromRestaurantCategory(RestaurantCategory category) {
    return new RestaurantCategoryResult(
        category.getName()
    );
  }
}
