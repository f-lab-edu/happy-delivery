package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Option;
import com.happy.delivery.domain.restaurant.OptionGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * OptionGroupResult.
 */
public class OptionGroupResult {

  private final Long id;
  private final Long menuId;
  private final String name;
  private final Integer limitOfOptions;
  private final Boolean mandatory;
  private List<OptionResult> optionResultList = new ArrayList<>();

  /**
   * OptionGroupResult Constructor.
   */
  public OptionGroupResult(Long id, Long menuId, String name, Integer limitOfOptions,
      Boolean mandatory, List<OptionResult> optionResultList) {
    this.id = id;
    this.menuId = menuId;
    this.name = name;
    this.limitOfOptions = limitOfOptions;
    this.mandatory = mandatory;
    this.optionResultList = optionResultList;
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
        changeOptionListToOptionResultList(optionGroup.getOptionList()));
  }

  /**
   * changeOptionListToOptionResultList.
   * 리스트 변형 : Option => OptionResul.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static List<OptionResult> changeOptionListToOptionResultList(List<Option> options) {
    List<OptionResult> results = new ArrayList<>();
    for (Option option : options) {
      results.add(OptionResult.fromOption(option));
    }
    return results;
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

  public List<OptionResult> getOptionResultList() {
    return optionResultList;
  }
}
