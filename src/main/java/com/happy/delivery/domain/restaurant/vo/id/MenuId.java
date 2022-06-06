package com.happy.delivery.domain.restaurant.vo.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * MenuId.
 * JPA 를 위한 클래스.
 * 복합 PK 를 사용하기 위해 만든 VO.
 */
public class MenuId implements Serializable {

  private Long menuId;

  private MenuGroupId menuGroup;

  public MenuId() {
  }

  public MenuId(Long menuId, MenuGroupId menuGroup) {
    this.menuId = menuId;
    this.menuGroup = menuGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuId menuId1 = (MenuId) o;
    return Objects.equals(menuId, menuId1.menuId) && Objects.equals(menuGroup,
        menuId1.menuGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(menuId, menuGroup);
  }
}
