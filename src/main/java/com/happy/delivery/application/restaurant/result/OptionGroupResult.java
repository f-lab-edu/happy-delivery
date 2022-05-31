package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Option;
import com.happy.delivery.domain.restaurant.OptionGroup;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * OptionGroupResult.
 */
public class OptionGroupResult implements Comparable<OptionGroupResult> {

  private final Long id;
  private final Long menuId;
  private final String name;
  private final Integer limitOfOptions;
  private final Boolean mandatory;
  private SortedSet<OptionResult> options;

  /**
   * OptionGroupResult Constructor.
   */
  public OptionGroupResult(Long id, Long menuId, String name, Integer limitOfOptions,
      Boolean mandatory, SortedSet<OptionResult> options) {
    this.id = id;
    this.menuId = menuId;
    this.name = name;
    this.limitOfOptions = limitOfOptions;
    this.mandatory = mandatory;
    this.options = options;
  }

  /**
   * OptionGroupResult fromOptionGroup.
   */
  public static OptionGroupResult fromOptionGroup(OptionGroup optionGroup) {
    return new OptionGroupResult(
        optionGroup.getId(),
        optionGroup.getMenu().getMenuId(),
        optionGroup.getName(),
        optionGroup.getLimitOfOptions(),
        optionGroup.getMandatory(),
        toOptionResultSet(optionGroup.getOptionSet()));
  }

  /**
   * toOptionResultSet.
   * Set 변형 : Option => OptionResul.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static SortedSet<OptionResult> toOptionResultSet(SortedSet<Option> options) {
    Iterator<Option> itr = options.iterator();
    SortedSet<OptionResult> results = new TreeSet<>();
    while (itr.hasNext()) {
      results.add(OptionResult.fromOption(itr.next()));
    }
    return results;
  }

  /**
   * compareTo.
   * MenuResult 의 TreeSet 을 정렬하는 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(OptionGroupResult o) {
    Long value = this.id - o.getId();
    return value.intValue();
  }

  public Long getId() {
    return id;
  }

  public Long getMenuId() {
    return menuId;
  }

  public String getName() {
    return name;
  }

  public Integer getLimitOfOptions() {
    return limitOfOptions;
  }

  public Boolean getMandatory() {
    return mandatory;
  }

  public SortedSet<OptionResult> getOptions() {
    return options;
  }
}
