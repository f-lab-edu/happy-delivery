package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.Option;
import com.happy.delivery.domain.restaurant.OptionGroup;


/**
 * OptionResult.
 */
public class OptionResult {

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
