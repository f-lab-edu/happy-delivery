package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.result.MenuResult;
import com.happy.delivery.domain.restaurant.Menu;
import com.happy.delivery.domain.restaurant.repository.MenuRepository;
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
  public List<MenuResult> menuView(Long storeId) {
    List<MenuResult> result = new ArrayList<>();
    List<Menu> menus = menuRepository.getAllByStoreId(storeId);
    for (Menu menu : menus) {
      result.add(MenuResult.fromMenu(menu));
    }
    return result;
  }
}
