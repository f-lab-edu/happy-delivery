package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.exception.restaurant.EmptyRestaurantListException;
import com.happy.delivery.domain.exception.restaurant.NoRestaurantsInThatCategoryException;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JpaRestaurantSearchRepositoryAdapter.
 */
public class JpaRestaurantSearchRepositoryAdapter implements RestaurantSearchRepository {

  private final JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository;
  private final JpaRestaurantRepository jpaRestaurantRepository;

  @Autowired
  public JpaRestaurantSearchRepositoryAdapter(
      JpaRestaurantCategoryRepository jpaRestaurantCategoryRepository,
      JpaRestaurantRepository jpaRestaurantRepository) {
    this.jpaRestaurantCategoryRepository = jpaRestaurantCategoryRepository;
    this.jpaRestaurantRepository = jpaRestaurantRepository;
  }

  @Override
  public List<RestaurantCategory> getAllCategories() {
    return jpaRestaurantCategoryRepository.findAll();
  }

  /**
   * getAllRestaurants.
   * cursor-pagination 을 이용하여
   * MySQL 에 있는 레스토랑 목록을 Redis 로 옮기는 메서드.
   * .
   * 모든 레스토랑 데이터를 한번에 옮기는 것은 많은 부하를 일으킨다.
   * 따라서 값을 나눠서 가져온다.
   */
  @Override
  public List<Restaurant> getAllRestaurants() {
    List<Restaurant> result = jpaRestaurantRepository.findTop10RestaurantsByOrderByIdAsc();

    if (result.isEmpty()) {
      throw new EmptyRestaurantListException("DB에 저장된 음식점이 없습니다.");
    }

    Long lastRestaurantId = result.get(result.size() - 1).getId();

    while (result.addAll(jpaRestaurantRepository.findTop10RestaurantsByIdGreaterThanOrderByIdAsc(
        lastRestaurantId))) {
      lastRestaurantId = result.get(result.size() - 1).getId();
    }

    return result;
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(List<Long> restaurantIdList,
      String category) {

    List<Restaurant> restaurantList = new ArrayList<>();

    for (Long restaurantId : restaurantIdList) {
      Restaurant restaurant =
          jpaRestaurantRepository.findRestaurantByIdAndCategory(restaurantId, category);
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
    return jpaRestaurantRepository.findAllById(restaurantId);
  }
}
