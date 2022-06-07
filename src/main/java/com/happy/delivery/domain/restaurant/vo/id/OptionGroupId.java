package com.happy.delivery.domain.restaurant.vo.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * OptionGroupId.
 * JPA 를 위한 클래스.
 * 복합 PK 를 사용하기 위해 만든 VO.
 */
public class OptionGroupId implements Serializable {

  private Long id;

  private MenuId menu;

  public OptionGroupId() {
  }

  public OptionGroupId(Long id, MenuId menu) {
    this.id = id;
    this.menu = menu;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionGroupId that = (OptionGroupId) o;
    return Objects.equals(id, that.id) && Objects.equals(menu, that.menu);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, menu);
  }
}
