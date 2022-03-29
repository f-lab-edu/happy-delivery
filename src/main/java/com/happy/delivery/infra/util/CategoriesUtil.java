package com.happy.delivery.infra.util;

import com.happy.delivery.domain.exception.user.UnexpectedCategoryException;

/**
 * CategoriesUtil.
 * int로 받은 카테고리 String으로 바꿔주는 class.
 */
public class CategoriesUtil {

  private static final String JAPANESE = "JAPANESE";
  private static final String CHINESE = "CHINESE";
  private static final String CHICKEN = "CHICKEN";
  private static final String KOREAN = "KOREAN";
  private static final String DESSERT = "DESSERT";
  private static final String SNACK = "SNACK";
  private static final String WESTERN = "WESTERN";
  private static final String ASIAN = "ASIAN";
  private static final String PIZZA = "PIZZA";

  /**
   * changeToStringCategory.
   * categoryId에 맞게 name 매칭.
   * 유효하지 않은 번호를 입력했을 때 오류 발생.
   */
  public static String changeToStringCategory(int category) {
    switch (category) {
      case 1:
        return JAPANESE;
      case 2:
        return CHINESE;
      case 3:
        return CHICKEN;
      case 4:
        return KOREAN;
      case 5:
        return DESSERT;
      case 6:
        return SNACK;
      case 7:
        return WESTERN;
      case 8:
        return ASIAN;
      case 9:
        return PIZZA;
      default:
        throw new UnexpectedCategoryException("범위 밖 카테고리입니다.");
    }
  }
}
