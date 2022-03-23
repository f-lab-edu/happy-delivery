package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.user.result.RestaurantCategoryResult;
import com.happy.delivery.application.user.result.RestaurantResult;
import java.util.List;

/**
 * UserRestaurantService.
 */
public interface RestaurantService {

  List<RestaurantCategoryResult> getCategories();

  List<RestaurantResult> restaurantSearchByCategory(Long categoryId, Long addressId);
}
