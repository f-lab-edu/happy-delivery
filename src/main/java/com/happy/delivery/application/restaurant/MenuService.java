package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.result.MenuResult;
import java.util.List;

/**
 * MenuService.
 */
public interface MenuService {

  List<MenuResult> menuView(Long storeId);
}
