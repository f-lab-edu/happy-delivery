package com.happy.delivery.application.user;

import com.happy.delivery.application.common.restaurant.command.RestaurantCommand;
import com.happy.delivery.application.common.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import java.util.List;

/**
 * RestaurantSearchService.
 */
public interface RestaurantSearchService {

  List<RestaurantCategoryResult> getCategories();

  List<RestaurantResult> restaurantSearchByCategory(String category,
      RestaurantCommand restaurantCommand);
}
