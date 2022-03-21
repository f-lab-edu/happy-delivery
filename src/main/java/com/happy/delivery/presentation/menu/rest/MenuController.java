package com.happy.delivery.presentation.menu.rest;

import com.happy.delivery.application.menu.MenuService;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.menu.request.MenuAddRequest;
import com.happy.delivery.presentation.menu.response.MenuResponse;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {

  private final MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  public ApiResponse MenuAdd(MenuAddRequest menuAddRequest){
    MenuResponse menuResponse = menuService.menuAdd(menuAddRequest.toCommand());
    return ApiResponse.success(menuResponse);
  }


}
