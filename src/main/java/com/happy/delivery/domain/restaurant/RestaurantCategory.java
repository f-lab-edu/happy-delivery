package com.happy.delivery.domain.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RestaurantCategory.
 */
@Entity
@Table(name = "restaurant_categories")
public class RestaurantCategory {

  @Id
  @Column(name = "category")
  private String name;

  /**
   * RestaurantCategory default constructor.
   * jpa에서 사용.
   */
  public RestaurantCategory() {
  }

  /**
   * RestaurantCategory constructor.
   */
  public RestaurantCategory(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "RestaurantCategory{" +
        "name='" + name + '\'' +
        '}';
  }
}
