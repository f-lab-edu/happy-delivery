package com.happy.delivery.infra.repository.user.hashmap;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * HashMapUserAddressRepository.
 * repository는 collection의 역할을 하기 때문에 비지니스 로직이 들어가면 안된다.
 */
public class HashMapUserAddressRepository implements UserAddressRepository {

  private final Map<Long, UserAddress> map = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

  /**
   * save.
   * 주소 저장 및 변경.
   */
  @Override
  public UserAddress save(UserAddress userAddress) {
    if ((userAddress.getId() == null) || (userAddress.getId() <= 0L)) {
      Long addressId = id.incrementAndGet();
      userAddress.setId(addressId);
      map.put(addressId, userAddress);
    } else {
      map.put(userAddress.getId(), userAddress);
    }
    return userAddress;
  }

  /**
   * findById.
   * 식별자를 통해 주소 검색.
   */
  @Override
  public UserAddress findById(Long id) {
    return map.get(id);
  }

  /**
   * findAllByUserId.
   * 회원 식별자를 통해 주소 검색.
   */
  @Override
  public List<UserAddress> findAllByUserId(Long userId) {
    return map.values()
        .stream()
        .filter(userAddress -> userId.equals(userAddress.getUserId()))
        .collect(Collectors.toList());
  }

  /**
   * findByUserIdAndMainAddressIsTrue.
   * 현재 주소로 지정된 주소 가져오는 메서드.
   */
  @Override
  public UserAddress findByUserIdAndMainAddressIsTrue(Long userId) {
    Optional<UserAddress> mainAddress = map.values()
        .stream()
        .filter(userAddress -> userId.equals(userAddress.getUserId()))
        .filter(userAddress -> userAddress.getMainAddress())
        .findFirst();
    return mainAddress.get();
  }

  /**
   * deleteById.
   * 식별자로 주소 삭제.
   */
  @Override
  public void deleteById(Long id) {
    map.remove(id);
  }

  // DB에 foreign key를 적용하면서 더이상 사용할 필요가 없어짐.
  //  public boolean deleteAllByUserId(Long userId) {
  //    boolean flag = false;
  //    List<UserAddress> addressList = new ArrayList<>();
  //    addressList = map.values()
  //        .stream()
  //        .filter(userAddress -> userId.equals(userAddress.getUserId()))
  //        .collect(Collectors.toList());
  //    for (UserAddress address : addressList) {
  //      if (map.remove(address.getId()) != null) {
  //        flag = true;
  //      }
  //    }
  //    return false;
  //  }
}
