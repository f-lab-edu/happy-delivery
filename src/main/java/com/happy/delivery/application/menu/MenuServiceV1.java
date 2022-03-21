package com.happy.delivery.application.menu;


import com.happy.delivery.application.menu.command.MenuAddCommand;
import com.happy.delivery.presentation.menu.response.MenuResponse;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceV1 implements MenuService{

  @Override
  public MenuResponse menuAdd(MenuAddCommand menuAddCommand) {
    return null;
  }
}
