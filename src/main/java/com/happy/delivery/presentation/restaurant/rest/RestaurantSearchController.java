package com.happy.delivery.presentation.restaurant.rest;

import com.happy.delivery.application.restaurant.RestaurantSearchService;
import com.happy.delivery.application.restaurant.result.MenuGroupResult;
import com.happy.delivery.application.restaurant.result.MenuResult;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.restaurant.request.RestaurantSearchRequest;
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
   * getRestaurantByCategoryIdAndPoint.
   * 근처 식당을 카테고리별로 가져옴.
   * 위치 정보를 어떻게 활용할 것인지 생각해봐야 함.
   */
  @GetMapping({"/{category}"})
  public ApiResponse getRestaurantByCategoryIdAndPoint(@PathVariable String category,
      @RequestBody RestaurantSearchRequest restaurantSearchRequest) {
    List<RestaurantResult> restaurants =
        restaurantSearchService.restaurantSearchByCategoryAndPoint(category,
            restaurantSearchRequest.toCommand());
    return ApiResponse.success(restaurants);
  }

  /**
   * getAllMenuGroupsByRestaurantId.
   * 해당 레스토랑의 메뉴들을 가져옴.
   */
  @GetMapping("/{restaurantId}/menus")
  public ApiResponse getAllMenuGroupsByRestaurantId(@PathVariable Long restaurantId) {
    List<MenuGroupResult> menuGroupResultList =
        restaurantSearchService.getAllMenuGroupsByRestaurantId(restaurantId);
    return ApiResponse.success(menuGroupResultList);
  }
}
