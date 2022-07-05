package com.happy.delivery.presentation.restaurant.rest;

import com.happy.delivery.application.restaurant.RestaurantSearchService;
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
   * init().
   * 레스토랑 위치 정보를 DB 에서 가져와 Redis 에 저장하는 메서드.
   */
  @GetMapping("/init")
  public ApiResponse init() {
    restaurantSearchService.init();
    return ApiResponse.success(null);
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
   * getRestaurantsByCategoryIdAndPoint.
   * 근처 식당 리스트를 카테고리별로 가져옴.
   * 위치 정보를 어떻게 활용할 것인지 생각해봐야 함.
   * ..
   * redis 가게 위치 정보 저장 + 가게 id
   * 가게 정보가 추가나 수정될 때마다 redis 에도 동일하게 적용해야함.
   * 위치 정보를 가지고 걸러주는 기능 있음.
   */
  @GetMapping({"/{category}"})
  public ApiResponse getRestaurantsByCategoryIdAndPoint(@PathVariable String category,
      @RequestBody RestaurantSearchRequest restaurantSearchRequest) {
    List<RestaurantResult> results =
        restaurantSearchService.getRestaurantsByCategoryAndPoint(category,
            restaurantSearchRequest.toCommand());
    return ApiResponse.success(results);
  }

  /**
   * getRestaurantAndMenus.
   * 해당 레스토랑의 상세정보과 메뉴들을 가져옴.
   */
  @GetMapping("/{restaurantId}/info")
  public ApiResponse getRestaurantAndMenus(@PathVariable Long restaurantId) {
    RestaurantResult result = restaurantSearchService.getRestaurantInfoAndAllMenus(restaurantId);
    return ApiResponse.success(result);
  }
}
