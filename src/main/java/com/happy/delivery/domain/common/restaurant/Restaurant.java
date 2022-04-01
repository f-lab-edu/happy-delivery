package com.happy.delivery.domain.common.restaurant;

/**
 * Restaurant.
 */
public class Restaurant {

  private final Long id;
  private final String name;
  private final String category;
  private final String addressDetail;

  /**
   * Restaurant Constructor.
   */
  public Restaurant(Long id, String name, String category, String addressDetail) {
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

  public String getAddressDetail() {
    return addressDetail;
  }

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", addressDetail='" + addressDetail + '\'' +
        '}';
  }
}
