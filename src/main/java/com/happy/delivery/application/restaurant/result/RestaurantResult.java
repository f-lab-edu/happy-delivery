package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Restaurant;

/**
 * RestaurantResult.
 */
public class RestaurantResult {

  private final Long id;
  private final String name;
  private final String category;
  private final String addressCode;

  /**
   * RestaurantResult Constructor.
   */
  public RestaurantResult(Long id, String name, String category, String addressCode) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.addressCode = addressCode;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public String getAddressCode() {
    return addressCode;
  }

  /**
   * fromRestaurant.
   * Restaurant -> RestaurantResult.
   */
  public static RestaurantResult fromRestaurant(Restaurant restaurant) {
    return new RestaurantResult(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getCategory(),
        restaurant.getAddressCode()
    );
  }

  @Override
  public String toString() {
    return "RestaurantResult{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", addressCode='" + addressCode + '\'' +
        '}';
  }
}
