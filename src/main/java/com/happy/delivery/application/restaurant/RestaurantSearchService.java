package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.command.RestaurantSearchCommand;
import com.happy.delivery.application.restaurant.result.MenuGroupResult;
import com.happy.delivery.application.restaurant.result.MenuResult;
import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import java.util.List;

/**
 * RestaurantSearchService.
 */
public interface RestaurantSearchService {

  List<RestaurantCategoryResult> getCategories();

  List<RestaurantResult> restaurantSearchByCategoryAndPoint(String category,
      RestaurantSearchCommand restaurantSearchCommand);

  List<MenuGroupResult> getAllMenuGroupsByRestaurantId(Long restaurantId);
}
