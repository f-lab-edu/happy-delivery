package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.Menu;
import java.util.List;

public interface MenuRepository {
  List<Menu> MenuSelect(Long storeId);
}
