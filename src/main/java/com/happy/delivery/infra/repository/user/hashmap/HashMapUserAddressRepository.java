package com.happy.delivery.infra.repository.user.hashmap;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 * HashMapUserAddressRepository.
 * repository는 collection의 역할을 하기때문에 비지니스 로직이 들어가면 안된다.
 */
@Repository
public class HashMapUserAddressRepository implements UserAddressRepository {

  private final Map<Long, UserAddress> map = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

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

  @Override
  public UserAddress findById(Long id) {
    return map.get(id);
  }

  @Override
  public UserAddress findByLastUsedAddress(Long userId) {
    return null;
  }

  @Override
  public List<UserAddress> findAllByUserId(Long userId) {
    return map.values()
        .stream()
        .filter(userAddress -> userId.equals(userAddress.getUserId()))
        .collect(Collectors.toList());
  }

  @Override
  public UserAddress deleteById(Long id) {
    UserAddress userAddress = map.remove(id);
    return userAddress;
  }
}
