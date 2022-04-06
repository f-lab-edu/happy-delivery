package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Restaurant;

/**
 * RestaurantResult.
 */
public class RestaurantResult {

  private final Long id;
  private final String name;
  private final String category;
  private final String addressDetail;

  /**
   * RestaurantResult Constructor.
   */
  public RestaurantResult(Long id, String name, String category, String addressDetail) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.addressDetail = addressDetail;
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
    return addressDetail;
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
        restaurant.getAddressDetail()
    );
  }

  @Override
  public String toString() {
    return "RestaurantResult{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
