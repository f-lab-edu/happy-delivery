package com.happy.delivery.application.common.menu;

import com.happy.delivery.application.common.menu.result.MenuResult;
import java.util.List;

/**
 * MenuService.
 */
public interface MenuService {

  List<MenuResult> menuView(Long storeId);
}
