package com.happy.delivery.infra.jpa.restaurant;

import com.happy.delivery.domain.restaurant.MenuGroup;
import com.happy.delivery.domain.restaurant.vo.MenuGroupId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaMenuRepository.
 */
public interface JpaMenuRepository extends JpaRepository<MenuGroup, MenuGroupId> {

  List<MenuGroup> findAllByRestaurantId(Long restaurantId);
}
