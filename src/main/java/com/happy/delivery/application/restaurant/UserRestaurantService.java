package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.restaurant.result.RestaurantCategoryResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import java.util.List;

/**
 * UserRestaurantService.
 */
public interface UserRestaurantService {

  List<RestaurantCategoryResult> getCategories();

  List<RestaurantResult> restaurantSearchByCategory(String category, Long addressId);
}
