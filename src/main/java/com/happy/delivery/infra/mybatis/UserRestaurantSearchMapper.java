package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * RestaurantSearchMapper.
 */
@Mapper
public interface UserRestaurantSearchMapper {

  List<RestaurantCategory> getAllCategories();

  List<Restaurant> getAllRestaurantsByCategory(Long category, String addressCode);
}
