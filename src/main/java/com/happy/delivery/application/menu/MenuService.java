package com.happy.delivery.application.menu;

import com.happy.delivery.application.menu.command.MenuViewCommand;
import com.happy.delivery.application.menu.result.MenuResult;
import java.util.List;

public interface MenuService {
  List<MenuResult> menuView(MenuViewCommand menuViewCommand);
}
