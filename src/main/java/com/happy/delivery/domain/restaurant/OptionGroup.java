package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.OptionGroupId;
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
 * OptionGroup.
 * 옵션들의 그룹.
 * ex) 맵기 단계 조절, 음료 추가 선택(최대 6개)
 */
@Entity
@IdClass(OptionGroupId.class)
public class OptionGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_group_id")
  private Long id;

  @Id
  @ManyToOne
  @JoinColumns(value = {
      @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id"),
      @JoinColumn(name = "menu_group_id", referencedColumnName = "menu_group_id"),
      @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
  })
  private Menu menu;

  @Column
  private String name;

  @Column(name = "limit_of_options")
  private Integer limitOfOptions;

  @Column
  private Boolean mandatory;

  public OptionGroup() {
  }

  /**
   * OptionGroup Args Constructor.
   */
  public OptionGroup(Long id, Menu menu, String name, Integer limitOfOptions,
      Boolean mandatory) {
    this.id = id;
    this.menu = menu;
    this.name = name;
    this.limitOfOptions = limitOfOptions;
    this.mandatory = mandatory;
  }

  public Long getId() {
    return id;
  }

  public Menu getMenu() {
    return menu;
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
}
