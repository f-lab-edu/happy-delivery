package com.happy.delivery.domain.menu.repository;

import com.happy.delivery.domain.menu.Menu;
import java.util.List;

/**
 * MenuRepository.
 */
public interface MenuRepository {
  List<Menu> getAllByStoreId(Long storeId);
}
