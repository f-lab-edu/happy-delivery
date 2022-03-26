package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.restaurant.RestaurantService;
import com.happy.delivery.infra.annotation.UserLoginCheck;
import com.happy.delivery.infra.util.SessionUtil;
import com.happy.delivery.presentation.common.response.ApiResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserRestaurantController.
 */
@RestController
@RequestMapping("/restaurant")
public class UserRestaurantController {

  private final Logger log = LoggerFactory.getLogger(UserRestaurantController.class);
  private final RestaurantService restaurantService;

  /**
   * UserRestaurantController Constructor.
   */
  @Autowired
  public UserRestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  /**
   * getRestaurantCategories.
   * 메인화면에서 큰 카테고리들을 조회.
   * Ex) 치킨, 피자, 중국집 등
   */
  @UserLoginCheck
  @GetMapping("/categories")
  public ApiResponse getRestaurantCategories() {
    return ApiResponse.success(restaurantService.getCategories());
  }

  /**
   * getRestaurantByCategoryIdAndTownCode.
   * 현재 주소 근처에 있는 식당을 카테고리별로 가져옴.
   */
  @UserLoginCheck
  @GetMapping("/{categoryId}/shops")
  public ApiResponse getRestaurantByCategoryIdAndTownCode(
      @PathVariable Long categoryId, HttpSession session) {
    //지금 main addressCode 가져오기
    Long addressId = SessionUtil.getAddressId(session);
    restaurantService.restaurantSearchByCategory(categoryId, addressId);
    return null;
  }
}
