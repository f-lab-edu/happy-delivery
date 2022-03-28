package com.happy.delivery.presentation.user.rest;

import com.happy.delivery.application.restaurant.UserRestaurantService;
import com.happy.delivery.application.restaurant.result.RestaurantResult;
import com.happy.delivery.domain.exception.user.UserAddressNotExistedException;
import com.happy.delivery.infra.annotation.UserLoginCheck;
import com.happy.delivery.infra.util.SessionUtil;
import com.happy.delivery.presentation.common.response.ApiResponse;
import java.util.List;
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
  @UserLoginCheck
  @GetMapping("/categories")
  public ApiResponse getRestaurantCategories() {
    return ApiResponse.success(userRestaurantService.getCategories());
  }

  /**
   * getRestaurantByCategoryIdAndTownCode.
   * 현재 주소 근처에 있는 식당을 카테고리별로 가져옴.
   */
  @UserLoginCheck
  @GetMapping("/{category}")
  public ApiResponse getRestaurantByCategoryIdAndTownCode(
      @PathVariable Long category, HttpSession session) {
    if (SessionUtil.getAddressId(session) == null) {
      throw new UserAddressNotExistedException("현재 주소가 존재하지 않습니다. 주소를 등록해주세요.");
    }
    List<RestaurantResult> restaurants =
        userRestaurantService.restaurantSearchByCategory(category,
            SessionUtil.getAddressId(session));
    return ApiResponse.success(restaurants);
  }
}
