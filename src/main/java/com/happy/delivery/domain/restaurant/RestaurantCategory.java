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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
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
  public RestaurantCategory(int id, String name) {
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
    return "RestaurantCategory{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
