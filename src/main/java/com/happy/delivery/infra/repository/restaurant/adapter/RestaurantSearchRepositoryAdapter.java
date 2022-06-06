package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.exception.restaurant.NoRestaurantsInThatCategoryException;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.mybatis.restaurant.RestaurantSearchMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * RestaurantSearchAdapter.
 */
public class RestaurantSearchRepositoryAdapter implements RestaurantSearchRepository {

  private final RestaurantSearchMapper restaurantSearchMapper;

  public RestaurantSearchRepositoryAdapter(RestaurantSearchMapper restaurantSearchMapper) {
    this.restaurantSearchMapper = restaurantSearchMapper;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return restaurantSearchMapper.getAllCategories();
  }

  @Override
  public List<Restaurant> getAllRestaurants() {
    return restaurantSearchMapper.getAllRestaurants();
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(List<Long> restaurantIdList,
      String category) {

    List<Restaurant> restaurantList = new ArrayList<>();

    for (Long restaurantId : restaurantIdList) {
      Restaurant restaurant =
          restaurantSearchMapper.getRestaurantsByIdAndCategory(restaurantId, category);
      if (restaurant != null) {
        restaurantList.add(restaurant);
      }
    }

    if (restaurantList.isEmpty()) {
      throw new NoRestaurantsInThatCategoryException("해당 카테고리에 배달 가능한 음식점이 없습니다.");
    }

    return restaurantList;
  }

  @Override
  public Restaurant getRestaurantInfoAndAllMenus(Long restaurantId) {
    return restaurantSearchMapper.getRestaurantInfoAndAllMenus(restaurantId);
  }
}
