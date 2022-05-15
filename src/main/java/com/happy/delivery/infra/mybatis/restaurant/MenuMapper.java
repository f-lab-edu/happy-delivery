package com.happy.delivery.infra.mybatis.restaurant;

import com.happy.delivery.domain.restaurant.MenuGroup;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * MenuMapper.
 */
@Mapper
public interface MenuMapper {
  List<MenuGroup> findAllByRestaurantId(Long restaurantId);
}
