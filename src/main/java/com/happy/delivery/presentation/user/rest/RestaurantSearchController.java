package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import com.happy.delivery.application.user.RestaurantSearchService;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.common.restaurant.request.RestaurantRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestaurantSearchController.
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantSearchController {

  private final Logger log = LoggerFactory.getLogger(RestaurantSearchController.class);
  private final RestaurantSearchService restaurantSearchService;

  /**
   * RestaurantSearchController Constructor.
   */
  @Autowired
  public RestaurantSearchController(RestaurantSearchService restaurantSearchService) {
    this.restaurantSearchService = restaurantSearchService;
  }

  /**
   * getRestaurantCategories.
   * 메인화면에서 큰 카테고리들을 조회.
   * Ex) 치킨, 피자, 중국집 등
   */
  @GetMapping("/categories")
  public ApiResponse getRestaurantCategories() {
    return ApiResponse.success(restaurantSearchService.getCategories());
  }

  /**
   * getRestaurantByCategoryIdAndTownCode.
   * 현재 주소 근처에 있는 식당을 카테고리별로 가져옴.
   */
  @GetMapping({"/{category}"})
  public ApiResponse getRestaurantByCategoryIdAndTownCode(@PathVariable String category,
      @RequestBody RestaurantRequest restaurantRequest) {
    List<RestaurantResult> restaurants =
        restaurantSearchService.restaurantSearchByCategory(category, restaurantRequest.toCommand());
    return ApiResponse.success(restaurants);
  }
}
