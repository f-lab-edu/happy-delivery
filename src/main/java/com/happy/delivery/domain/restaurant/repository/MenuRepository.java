package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.MenuGroup;
import java.util.List;

/**
 * MenuRepository.
 */
public interface MenuRepository {

  List<MenuGroup> getAllMenuGroupsByRestaurantId(Long restaurantId);

}
