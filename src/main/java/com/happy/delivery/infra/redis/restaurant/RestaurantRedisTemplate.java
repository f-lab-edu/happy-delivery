package com.happy.delivery.infra.redis.restaurant;

import com.happy.delivery.domain.exception.restaurant.NearbyRestaurantNotExistException;
import com.happy.delivery.domain.restaurant.Restaurant;
import com.happy.delivery.domain.restaurant.repository.RestaurantCacheRepository;
import com.happy.delivery.domain.restaurant.vo.RestaurantSearchValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisGeoCommands.DistanceUnit;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * RestaurantRedisTemplate.
 * 레스토랑의 longitude, latitude, id를 Redis 에 저장하기 위해서 만든 RedisTemplate.
 */
@Repository
public class RestaurantRedisTemplate implements RestaurantCacheRepository {

  private final RedisTemplate<String, String> restaurantPointRedisTemplate;
  private final RedisSerializer<String> stringSerializer;
  private static final String GEO_KEY = "restaurants";

  @Autowired
  public RestaurantRedisTemplate(RedisTemplate<String, String> restaurantPointRedisTemplate) {
    this.restaurantPointRedisTemplate = restaurantPointRedisTemplate;
    this.stringSerializer = restaurantPointRedisTemplate.getStringSerializer();
  }

  /*
         redisTemplate.opsFor*() :
             redisTemplate 에는 redis 가 제공하는 list, set, sortedSet, hash... 와 같은 다양한 command 를
             지원하기 위해 opsFor* 메서드가 존재한다.
             사용하고자 하는 redis command 에 대응되는 메서드를 호출하여 사용하면 된다.
             해당 메서드를 호출하면 각 redis command 에 대응된 operation 객체가 반환된다.

         Bound*Operations :
             사용하다 보면 key 가 변하지 않고 value 만 반복적으로 처리하는 경우가 있다.
             이 경우 매번 반복으로 사용하는 key 를 상수값으로 method 에 넘기지 않고 아예 operation 자체가 들고 있게 하여
             key 를 제외한 값만 method 로 호출하여 사용하도록 간소화할 수 있도록 Bound*Operation 을 제공한다.
             thread-safe 하며, 재사용이 가능하다.

         geospatial : 지도 위의 object 위치, 지리 데이터.

         Redis 에서 geospatial data 를 저장하는 방법 :
                   Redis 에서 사용하는 geospatial data 는 longitude, latitude, name(String) 로 구성된다.
                   Redis 는 Geohash 를 사용해서 geospatial data 를 최대 12자리의 String 으로 hash 한다.
                   이 hashed string 은 지정한 key 의 sorted set 에 저장한다.
                   따라서, Redis Geo 를 사용할 때엔 RedisTemplate<String, String>을 사용해야 한다.

   */
  @Override
  public void save(List<Restaurant> restaurants) {

    Map<String, Point> pointMap = new HashMap<>();

    for (int i = 0; i < restaurants.size(); i++) {
      Point point = new Point(restaurants.get(i).getLongitude(), restaurants.get(i).getLatitude());
      pointMap.put(restaurants.get(i).getId().toString(), point);
    }

    restaurantPointRedisTemplate.opsForGeo().add(GEO_KEY, pointMap);

  }

  @Override
  public List<Long> searchNearbyRestaurants(RestaurantSearchValue rsv) {
    // 현재 주문 위치
    Point point = new Point(rsv.getLongitude(), rsv.getLatitude());
    // 거리 설정
    Distance distance = new Distance(rsv.getDistance(), DistanceUnit.METERS);
    // 범위
    Circle within = new Circle(point, distance);

    GeoResults<GeoLocation<String>> radius =
        restaurantPointRedisTemplate.opsForGeo().radius(GEO_KEY, within);

    if (radius == null) {
      throw new NearbyRestaurantNotExistException("근처에 주문할 수 있는 레스토랑이 없습니다.");
    }

    List<Long> nearbyRestaurantIdList = new ArrayList<>();
    radius.forEach(geoLocationGeoResult -> {
      RedisGeoCommands.GeoLocation<String> content = geoLocationGeoResult.getContent();
      String id = content.getName();
      nearbyRestaurantIdList.add(Long.parseLong(id));
    });

    return nearbyRestaurantIdList;
  }
}
