package com.happy.delivery.domain.restaurant;

import com.happy.delivery.domain.restaurant.vo.MenuGroupId;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * MenuGroups.
 * 메뉴들의 묶음.
 * Ex) 특선메뉴, 인기메뉴, 기본메뉴 등.
 */
@Entity(name = "menu_groups")
@IdClass(MenuGroupId.class)
public class MenuGroup implements Comparable<MenuGroup> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_group_id")
  private Long id;

  /*
        JoinColumn : 외래키를 갖는 칼럼을 지정한다.

        양방향 연관관계 주인 : 연관관계의 주인만이 외래키를 관리(등록, 수정)할 수 있다.
                          주인은 mappedBy를 사용할 수 없다.
                          양방향 매핑시 연관관계의 주인에 값을 입력해야 한다.


        @ManyToOne, @OneToOne 는 기본이 즉시 로딩이므로 지연 로딩으로 설정해주자!

        FetchType.LAZY :  지연로딩 설정, 실제로 엔티티가 필요한 시점에 값을 가져오도록 한다.
                          가급적 지연 로딩만 사용하자! => 즉시 로딩은 불필요한 객체 참조로 인해 메모리가 많이 차지한다는 단점이 있다.

        BUT! 레스토랑 정보를 가져올 때 MenuGroup, Menu, OptionGroup, Option 을  함께 사용하는데 어떻게 해야 할까?
        이런 경우를 위해서 JPQL 의 fetch join 이나 @EntityGraph 을 이용해 한방 쿼리로 가져와서 쓸 수 있다.

  */
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Column
  private String name;

  @OneToMany(mappedBy = "menuGroup")
  Set<Menu> menuSet = new TreeSet<>();

  /**
   * MenuGroups default constructor.
   */
  public MenuGroup() {
  }

  /**
   * MenuGroups Args constructor.
   */
  public MenuGroup(Long id, Restaurant restaurant, String name) {
    this.id = id;
    this.restaurant = restaurant;
    this.name = name;
  }

  /**
   * compareTo.
   * Restaurant 의 TreeSet 을 정렬하는 메서드.
   * * 오름차순 방식 *
   * 객체가 같으면 0 리턴.
   * 내 값이 주어진 객체보다 작으면 음수 리턴.
   * 내 값이 주어진 객체보다 크면 양수 리턴.
   * => 내림차순을 원한다면 반대로 만들 것!
   */
  @Override
  public int compareTo(MenuGroup o) {
    Long value = this.id - o.getId();
    return value.intValue();
  }

  /*
        equals 와 hashcode 에서 PK 와 관련된 필드만 사용하는 이유 :
                  entity 는 현실에 있는 사물 등을 컴퓨터가 취급할 수 있도록 바꾼 것 + DB 테이블 레코드이다.
                  PK와 관련된 값이 변한다면 레코드의 고유성에 문제가 생기지만, 그밖의 값이 변경되었을 땐 고유성에 영향을 주지 않는다.
                  따라서, equals 와 hashcode 를 사용해 고유성을 확인하고 싶다면 식별자 필드만 써도 충분하다.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuGroup menuGroup = (MenuGroup) o;
    return Objects.equals(id, menuGroup.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Long getId() {
    return id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public String getName() {
    return name;
  }

  public Set<Menu> getMenuSet() {
    return menuSet;
  }
}
