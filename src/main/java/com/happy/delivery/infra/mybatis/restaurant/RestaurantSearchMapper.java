package com.happy.delivery.infra.mybatis.restaurant;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * RestaurantSearchMapper.
 */
@Mapper
public interface RestaurantSearchMapper {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurants();

  Restaurant getRestaurantsByIdAndCategory(Long restaurantId, String category);

  Restaurant getRestaurantInfoAndAllMenus(Long restaurantId);
}
