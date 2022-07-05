package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.exception.restaurant.NoRestaurantsInThatCategoryException;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantCategoryRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaRestaurantRepository;
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

  @Override
  public List<Restaurant> getTop10Restaurants() {
    return jpaRestaurantRepository.findTop10RestaurantsByOrderByIdAsc();
  }

  public List<Restaurant> getTop10RestaurantsById(Long lastRestaurantId) {
    return jpaRestaurantRepository
        .findTop10RestaurantsByIdGreaterThanOrderByIdAsc(lastRestaurantId);
  }

  @Override
  public List<Restaurant> getAllRestaurantsByCategory(List<Long> restaurantIdList,
      String category) {

    List<Restaurant> restaurantList =
        jpaRestaurantRepository.findRestaurantsByIdInAndCategory(restaurantIdList, category);

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
