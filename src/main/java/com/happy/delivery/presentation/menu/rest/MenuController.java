package com.happy.delivery.presentation.menu.rest;

import com.happy.delivery.application.menu.MenuService;
import com.happy.delivery.application.menu.result.MenuResult;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.menu.request.MenuViewRequest;
import com.happy.delivery.presentation.menu.response.MenuResponse;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

  private final MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @PostMapping("/view")
  public ApiResponse MenuView(@RequestBody MenuViewRequest menuViewRequest){
    List<MenuResult> menuResults = menuService.menuView(menuViewRequest.toCommand());
    return ApiResponse.success(menuResults);
  }


}
