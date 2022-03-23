package com.happy.delivery.application.menu;


import com.happy.delivery.application.menu.command.MenuViewCommand;
import com.happy.delivery.application.menu.result.MenuResult;
import com.happy.delivery.domain.user.Menu;
import com.happy.delivery.domain.user.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MenuServiceV1.
 */
@Service
public class MenuServiceV1 implements MenuService {

  private final MenuRepository menuRepository;

  @Autowired
  public MenuServiceV1(MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }


  @Override
  public List<MenuResult> menuView(MenuViewCommand menuViewCommand) {
    List<MenuResult> result = new ArrayList<>();
    //대체 storeId;
    List<Menu> menus = menuRepository.menuSelect(menuViewCommand.getMenuId());
    for (Menu menu : menus) {
      result.add(MenuResult.fromMenu(menu));
    }
    return null;
  }
}
