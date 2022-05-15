package com.happy.delivery.infra.repository.restaurant.adapter;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.repository.MenuRepository;
import com.happy.delivery.infra.mybatis.restaurant.MenuMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MenuRepositoryAdapter.
 */
public class MenuRepositoryAdapter implements MenuRepository {

  private final MenuMapper menuMapper;

  @Autowired
  public MenuRepositoryAdapter(MenuMapper menuMapper) {
    this.menuMapper = menuMapper;
  }

  @Override
  public List<MenuGroup> getAllMenuGroupsByRestaurantId(Long restaurantId) {
    return menuMapper.findAllByRestaurantId(restaurantId);
  }
}
