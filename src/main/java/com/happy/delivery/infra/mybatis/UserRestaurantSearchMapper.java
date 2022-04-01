package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.common.restaurant.Restaurant;
import com.happy.delivery.domain.common.restaurant.RestaurantCategory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * RestaurantSearchMapper.
 */
@Mapper
public interface UserRestaurantSearchMapper {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurantsByCategory(String category, Double longitude, Double latitude);
}
