package com.happy.delivery.application.menu;

import com.happy.delivery.application.menu.command.MenuAddCommand;
import com.happy.delivery.presentation.menu.response.MenuResponse;

public interface MenuService {
  MenuResponse menuAdd(MenuAddCommand menuAddCommand);
}
