package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.Menu;
import java.util.List;

/**
 * MenuRepository.
 */
public interface MenuRepository {
  List<Menu> getAllByStoreId(Long storeId);
}
