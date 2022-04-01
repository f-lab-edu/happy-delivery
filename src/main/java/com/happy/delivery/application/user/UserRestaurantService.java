package com.happy.delivery.application.user;

import com.happy.delivery.application.common.restaurant.command.RestaurantLocationCommand;
import com.happy.delivery.application.common.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import java.util.List;

/**
 * UserRestaurantService.
 */
public interface UserRestaurantService {

  List<RestaurantCategoryResult> getCategories();

  List<RestaurantResult> restaurantSearchByCategory(String category,
      RestaurantLocationCommand locationCommand);
}
