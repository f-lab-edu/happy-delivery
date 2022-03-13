package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.infra.mybatis.UserAddressMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserAddressRepositoryAdapter.
 */
@Repository
public class UserAddressRepositoryAdapter implements UserAddressRepository {

  private final UserAddressMapper userAddressMapper;

  public UserAddressRepositoryAdapter(
      UserAddressMapper userAddressMapper) {
    this.userAddressMapper = userAddressMapper;
  }

  @Override
  public void save(UserAddress userAddress) {
    if ((userAddress.getId() == null) || (userAddress.getId() <= 0L)) {
      userAddressMapper.insert(userAddress);
    }
    userAddressMapper.update(userAddress);
  }

  @Override
  public UserAddress findById(Long id) {
    return userAddressMapper.findById(id);
  }

  @Override
  public List<UserAddress> findAllByUserId(Long userId) {
    return userAddressMapper.findAllByUserId(userId);
  }

  @Override
  public void deleteById(Long id) {
    userAddressMapper.deleteById(id);
  }
}
