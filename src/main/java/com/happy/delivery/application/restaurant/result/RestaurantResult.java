package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.Restaurant;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * RestaurantResult.
 */
public class RestaurantResult {

  private final Long id;
  private final String name;
  private final String category;
  private final Double longitude;
  private final Double latitude;
  private final String addressDetail;
  private Set<MenuGroupResult> menuGroups;

  /**
   * RestaurantResult Constructor.
   */
  public RestaurantResult(Long id, String name, String category, Double longitude,
      Double latitude, String addressDetail, Set<MenuGroupResult> menuGroups) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
    this.menuGroups = menuGroups;
  }

  /**
   * fromRestaurantForList.
   * Restaurant -> RestaurantResult.
   * 식당 정보를 가져올 때, 메뉴 정보들은 가져오지 않는 메서드.
   * 쿼리문을 1번만 생성.
   */
  public static RestaurantResult fromRestaurantForList(Restaurant restaurant) {
    return new RestaurantResult(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getCategory(),
        restaurant.getLongitude(),
        restaurant.getLatitude(),
        restaurant.getAddressDetail(),
        null
    );
  }

  /**
   * fromRestaurantForList.
   * Restaurant -> RestaurantResult.
   * 식당 정보를 가져올 때, 메뉴 정보까지 가져오는 메서드.
   * 쿼리문을 여러개 생성함.
   */
  public static RestaurantResult fromRestaurant(Restaurant restaurant) {
    return new RestaurantResult(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getCategory(),
        restaurant.getLongitude(),
        restaurant.getLatitude(),
        restaurant.getAddressDetail(),
        toMenuGroupResultSet(restaurant.getMenuGroupSet()));
  }

  /**
   * toMenuGroupResultSet.
   * set 변형 : MenuGroup => MenuGroupResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static Set<MenuGroupResult> toMenuGroupResultSet(Set<MenuGroup> menuGroups) {
    Iterator<MenuGroup> itr = menuGroups.iterator();
    Set<MenuGroupResult> results = new TreeSet<>();
    while (itr.hasNext()) {
      results.add(MenuGroupResult.fromMenuGroup(itr.next()));
    }
    return results;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getAddressDetail() {
    return addressDetail;
  }

  public Set<MenuGroupResult> getMenuGroups() {
    return menuGroups;
  }
}
