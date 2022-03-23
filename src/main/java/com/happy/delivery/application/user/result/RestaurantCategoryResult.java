package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.restaurant.RestaurantCategory;

/**
 * RestaurantCategoryResult.
 */
public class RestaurantCategoryResult {
  private Long id;
  private String name;

  /**
   * RestaurantCategoryResult constructor.
   */
  public RestaurantCategoryResult(Long id, String name) {
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
