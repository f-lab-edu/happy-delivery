package com.happy.delivery.domain.restaurant.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * MenuGroupId.
 * JPA 를 위한 클래스.
 * 복합 PK 를 사용하기 위해 만든 VO.
 */
public class MenuGroupId implements Serializable {

  private Long id;

  private Long restaurant;

  public MenuGroupId() {
  }

  public MenuGroupId(Long id, Long restaurant) {
    this.id = id;
    this.restaurant = restaurant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuGroupId that = (MenuGroupId) o;
    return Objects.equals(id, that.id) && Objects.equals(restaurant,
        that.restaurant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, restaurant);
  }
}
