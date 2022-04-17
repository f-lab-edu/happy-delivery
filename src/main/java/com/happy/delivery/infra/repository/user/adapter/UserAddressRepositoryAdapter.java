package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.infra.mybatis.user.UserAddressMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * UserAddressRepositoryAdapter.
 */
@Repository
public class UserAddressRepositoryAdapter implements UserAddressRepository {

  private final UserAddressMapper userAddressMapper;

  public UserAddressRepositoryAdapter(UserAddressMapper userAddressMapper) {
    this.userAddressMapper = userAddressMapper;
  }

  @Override
  public UserAddress save(UserAddress userAddress) {
    if ((userAddress.getId() == null) || (userAddress.getId() <= 0L)) {
      userAddressMapper.insert(userAddress);
    } else {
      userAddressMapper.update(userAddress);
    }
    return userAddress;
  }

  @Override
  public Optional<UserAddress> findById(Long id) {
    UserAddress userAddress = userAddressMapper.findById(id);
    return Optional.ofNullable(userAddress);
  }

  @Override
  public List<UserAddress> findByUserId(Long userId) {
    return userAddressMapper.findByUserId(userId);
  }

  @Override
  public UserAddress findMainAddress(Long userId) {
    return userAddressMapper.findMainAddress(userId);
  }

  @Override
  public void deleteById(Long id) {
    userAddressMapper.deleteById(id);
  }
}
