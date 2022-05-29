package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.OptionGroupId;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * OptionGroup.
 * 옵션들의 그룹.
 * ex) 맵기 단계 조절, 음료 추가 선택(최대 6개)
 */
@Entity(name = "option_groups")
@IdClass(OptionGroupId.class)
public class OptionGroup implements Comparable<OptionGroup> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_group_id")
  private Long id;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
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

  @OneToMany(mappedBy = "optionGroup")
  private Set<Option> optionSet = new TreeSet<>();

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

  /**
   * compareTo.
   * Menu 의 TreeSet 을 정렬하는 메서드.
   * 오름차순.
   */
  @Override
  public int compareTo(OptionGroup o) {
    Long value = this.id - o.getId();
    return value.intValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionGroup that = (OptionGroup) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
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

  public Set<Option> getOptionSet() {
    return optionSet;
  }
}
