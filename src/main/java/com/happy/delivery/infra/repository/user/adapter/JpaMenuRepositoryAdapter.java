package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.repository.MenuRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaMenuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JpaMenuRepositoryAdapter.
 * JpaMenuRepository 를 MenuRepository 와 연결해주는 class.
 */
public class JpaMenuRepositoryAdapter implements MenuRepository {

  private final JpaMenuRepository jpaMenuRepository;

  @Autowired
  public JpaMenuRepositoryAdapter(JpaMenuRepository jpaMenuRepository) {
    this.jpaMenuRepository = jpaMenuRepository;
  }

  @Override
  public List<MenuGroup> getAllMenuGroupsByRestaurantId(Long restaurantId) {
    return jpaMenuRepository.findAllByRestaurantId(restaurantId);
  }
}
