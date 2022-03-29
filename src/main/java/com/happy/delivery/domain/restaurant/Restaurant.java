package com.happy.delivery.domain.restaurant;

/**
 * Restaurant.
 */
public class Restaurant {

  private final Long id;
  private final String name;
  private final String category;
  private final String addressCode;

  /**
   * Restaurant Constructor.
   */
  public Restaurant(Long id, String name, String category, String addressCode) {
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

  @Override
  public String toString() {
    return "Restaurant{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", addressCode='" + addressCode + '\'' +
        '}';
  }
}
