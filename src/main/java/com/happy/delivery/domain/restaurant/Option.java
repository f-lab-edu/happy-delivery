package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.OptionId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Option.
 * 옵션.
 * ex) 안 매움, 보통, 약간 매움, 매움, 아주 매움
 */
@Entity
@IdClass(OptionId.class)
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_id")
  private Long id;

  @Id
  @ManyToOne
  @JoinColumns(value = {
      @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id"),
      @JoinColumn(name = "menu_group_id", referencedColumnName = "menu_group_id"),
      @JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
      @JoinColumn(name = "option_group_id", referencedColumnName = "option_group_id")
  })
  private OptionGroup optionGroup;

  @Column
  private String name;

  @Column
  private Integer price;

  public Option() {
  }

  /**
   * Option Args Constructor.
   */
  public Option(Long id, OptionGroup optionGroup, String name, Integer price) {
    this.id = id;
    this.optionGroup = optionGroup;
    this.name = name;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public OptionGroup getOptionGroup() {
    return optionGroup;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }
}
