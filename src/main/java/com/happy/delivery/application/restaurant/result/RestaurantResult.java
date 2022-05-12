package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Restaurant;

/**
 * RestaurantResult.
 */
public class RestaurantResult {

  private final Long id;
  private final String name;
  private final String category;
  private final Double longitude;
  private final Double latitude;
  private final String addressDetail;

  /**
   * RestaurantResult Constructor.
   */
  public RestaurantResult(Long id, String name, String category, Double longitude,
      Double latitude, String addressDetail) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
  }

  /**
   * fromRestaurant. Restaurant -> RestaurantResult.
   */
  public static RestaurantResult fromRestaurant(Restaurant restaurant) {
    return new RestaurantResult(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getCategory(),
        restaurant.getLongitude(),
        restaurant.getLatitude(),
        restaurant.getAddressDetail()
    );
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

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "RestaurantResult{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
