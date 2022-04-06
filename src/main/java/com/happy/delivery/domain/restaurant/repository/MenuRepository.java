package com.happy.delivery.domain.restaurant.repository;

import com.happy.delivery.domain.restaurant.Menu;
import java.util.List;

/**
 * MenuRepository.
 */
public interface MenuRepository {
  List<Menu> getAllByStoreId(Long storeId);
}
