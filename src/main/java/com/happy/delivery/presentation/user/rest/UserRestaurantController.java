package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.common.restaurant.result.RestaurantResult;
import com.happy.delivery.application.user.UserRestaurantService;
import com.happy.delivery.presentation.common.response.ApiResponse;
import com.happy.delivery.presentation.common.restaurant.request.RestaurantLocationRequest;
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
 * UserRestaurantController.
 */
@RestController
@RequestMapping("/restaurants")
public class UserRestaurantController {

  private final Logger log = LoggerFactory.getLogger(UserRestaurantController.class);
  private final UserRestaurantService userRestaurantService;

  /**
   * UserRestaurantController Constructor.
   */
  @Autowired
  public UserRestaurantController(UserRestaurantService userRestaurantService) {
    this.userRestaurantService = userRestaurantService;
  }

  /**
   * getRestaurantCategories.
   * 메인화면에서 큰 카테고리들을 조회.
   * Ex) 치킨, 피자, 중국집 등
   */
  @GetMapping("/categories")
  public ApiResponse getRestaurantCategories() {
    return ApiResponse.success(userRestaurantService.getCategories());
  }

  /**
   * getRestaurantByCategoryIdAndTownCode.
   * 현재 주소 근처에 있는 식당을 카테고리별로 가져옴.
   */
  @GetMapping({"/{category}"})
  public ApiResponse getRestaurantByCategoryIdAndTownCode(@PathVariable String category,
      @RequestBody RestaurantLocationRequest locationRequest) {
    List<RestaurantResult> restaurants =
        userRestaurantService.restaurantSearchByCategory(category, locationRequest.toCommand());
    return ApiResponse.success(restaurants);
  }
}
