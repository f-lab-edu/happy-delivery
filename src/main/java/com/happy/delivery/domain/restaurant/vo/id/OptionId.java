package com.happy.delivery.domain.restaurant.vo.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * OptionId.
 * JPA 를 위한 클래스.
 * 복합 PK 를 사용하기 위해 만든 VO.
 */
public class OptionId implements Serializable {

  private Long id;

  private OptionGroupId optionGroup;

  public OptionId() {
  }

  public OptionId(Long id, OptionGroupId optionGroup) {
    this.id = id;
    this.optionGroup = optionGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionId optionId = (OptionId) o;
    return Objects.equals(id, optionId.id) && Objects.equals(optionGroup,
        optionId.optionGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, optionGroup);
  }
}
