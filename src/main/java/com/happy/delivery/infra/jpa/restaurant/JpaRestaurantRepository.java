package com.happy.delivery.infra.jpa.restaurant;

import com.happy.delivery.domain.restaurant.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRestaurantRepository.
 */
public interface JpaRestaurantRepository extends JpaRepository<Restaurant, Long> {

  List<Restaurant> findTop10RestaurantsByOrderByIdAsc();

  List<Restaurant> findTop10RestaurantsByIdGreaterThanOrderByIdAsc(Long id);

  List<Restaurant> findRestaurantsByIdInAndCategory(List<Long> restaurantIdList, String category);

  /**
   * n + 1 문제를 해결하기 위해서 fetch-join 나 @EntityGraph 를 사용할 수 있다.
   * fetch-join 은 inner join 으로 값을 가져오고, @EntityGraph 은 outer join 으로 값을 가져온다.
   * 여기서는 @EntityGraph 를 사용했는데, 누락되는 값 없도록 하기 위해서이다.
   * .
   *  fetch-join query ::
   *  select distinct r from restaurants as r join fetch r.menuGroupSet mgs
   *  join fetch mgs.menuSet ms join fetch ms.optionGroupSet ops
   *  join fetch ops.optionSet where r.id = :restaurantId
   */
  @EntityGraph(attributePaths = {"menuGroupSet", "menuGroupSet.menuSet",
      "menuGroupSet.menuSet.optionGroupSet", "menuGroupSet.menuSet.optionGroupSet.optionSet"})
  Restaurant findAllById(Long restaurantId);
}
