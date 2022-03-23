package com.happy.delivery.application.restaurant;

import com.happy.delivery.application.user.result.RestaurantCategoryResult;
import com.happy.delivery.application.user.result.RestaurantResult;
import com.happy.delivery.domain.restaurant.RestaurantCategory;
import com.happy.delivery.domain.restaurant.repository.RestaurantSearchRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RestaurantServiceV1.
 */
@Service
public class RestaurantServiceV1 implements RestaurantService {

  private final RestaurantSearchRepository restaurantSearchRepository;

  /**
   * RestaurantServiceV1 constructor.
   */
  @Autowired
  public RestaurantServiceV1(RestaurantSearchRepository restaurantSearchRepository) {
    this.restaurantSearchRepository = restaurantSearchRepository;
  }

  @Override
  public List<RestaurantCategoryResult> getCategories() {
    List<RestaurantCategory> categories = restaurantSearchRepository.getAllCategories();
    List<RestaurantCategoryResult> result = new ArrayList<>();
    for (RestaurantCategory category : categories) {
      result.add(RestaurantCategoryResult.fromRestaurantCategory(category));
    }
    return result;
  }

  @Override
  public List<RestaurantResult> restaurantSearchByCategory(Long categoryId, Long addressId) {
    return null;
  }


}
