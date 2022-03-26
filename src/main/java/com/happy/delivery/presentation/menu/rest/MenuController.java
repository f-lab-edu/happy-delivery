package com.happy.delivery.presentation.menu.rest;

import com.happy.delivery.application.menu.MenuService;
import com.happy.delivery.application.menu.result.MenuResult;
import com.happy.delivery.presentation.common.response.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MenuController.
 */

@RestController
@RequestMapping("/menu")
public class MenuController {

  private final MenuService menuService;

  /**
   * MenuConstructor.
   */
  @Autowired
  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping("/view/{storeId}")
  public ApiResponse menuView(@PathVariable Long storeId) {
    List<MenuResult> menuResults = menuService.menuView(storeId);
    return ApiResponse.success(menuResults);
  }
}
