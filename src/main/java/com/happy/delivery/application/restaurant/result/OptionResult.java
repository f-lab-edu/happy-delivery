package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Option;


/**
 * OptionResult.
 */
public class OptionResult implements Comparable<OptionResult> {

  private Long id;
  private Long optionGroupId;
  private String name;
  private Integer price;

  /**
   * OptionResult Args Constructor.
   */
  public OptionResult(Long id, Long optionGroupId, String name, Integer price) {
    this.id = id;
    this.optionGroupId = optionGroupId;
    this.name = name;
    this.price = price;
  }

  /**
   * OptionResult fromOption.
   */
  public static OptionResult fromOption(Option option) {
    return new OptionResult(
        option.getId(),
        option.getOptionGroup().getId(),
        option.getName(),
        option.getPrice());
  }

  /**
   * compareTo.
   * OptionGroupResult 의 TreeSet 을 정렬하는 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(OptionResult o) {
    Long value = this.id - o.getId();
    return value.intValue();
  }

  public Long getId() {
    return id;
  }

  public Long getOptionGroupId() {
    return optionGroupId;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }
}
