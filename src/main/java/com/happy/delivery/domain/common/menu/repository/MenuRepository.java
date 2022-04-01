package com.happy.delivery.domain.common.menu.repository;

import com.happy.delivery.domain.common.menu.Menu;
import java.util.List;

/**
 * MenuRepository.
 */
public interface MenuRepository {
  List<Menu> getAllByStoreId(Long storeId);
}
