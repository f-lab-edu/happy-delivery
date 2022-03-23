package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.Menu;
import com.happy.delivery.domain.user.repository.MenuRepository;
import com.happy.delivery.infra.mybatis.MenuMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MenuRepositoryAdapter implements MenuRepository {
  private final MenuMapper menuMapper;

  @Autowired
  public MenuRepositoryAdapter(MenuMapper menuMapper) {
    this.menuMapper = menuMapper;
  }

  @Override
  public List<Menu> MenuSelect(Long storeId) {
    List<Menu> menus = menuMapper.MenuSelect(storeId);
    return menus;
  }
}
