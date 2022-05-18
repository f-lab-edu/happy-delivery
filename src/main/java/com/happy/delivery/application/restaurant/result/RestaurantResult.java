package com.happy.delivery.application.restaurant.result;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.Restaurant;
import java.util.ArrayList;
import java.util.List;

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
  private List<MenuGroupResult> menuGroupResultList = new ArrayList<>();

  /**
   * RestaurantResult Constructor.
   */
  public RestaurantResult(Long id, String name, String category, Double longitude,
      Double latitude, String addressDetail, List<MenuGroupResult> menuGroupResultList) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
    this.menuGroupResultList = menuGroupResultList;
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
        changeMenuGroupListToMenuGroupResultList(restaurant.getMenuGroupList()));
  }

  /**
   * changeMenuGroupListToMenuGroupResultList.
   * 리스트 변형 : MenuGroup => MenuGroupResult.
   * stack-over-flow 를 예방하고, 계층을 변경할 수 있음.
   */
  private static List<MenuGroupResult> changeMenuGroupListToMenuGroupResultList(
      List<MenuGroup> menuGroups) {
    List<MenuGroupResult> results = new ArrayList<>();
    for (MenuGroup menuGroup : menuGroups) {
      results.add(MenuGroupResult.fromMenuGroup(menuGroup));
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

  public List<MenuGroupResult> getMenuGroupResultList() {
    return menuGroupResultList;
  }
}
