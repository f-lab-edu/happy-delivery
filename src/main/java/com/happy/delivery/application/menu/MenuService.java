package com.happy.delivery.application.menu;

import com.happy.delivery.application.menu.result.MenuResult;
import java.util.List;

/**
 * MenuService.
 */
public interface MenuService {

  List<MenuResult> menuView(Long storeId);
}
