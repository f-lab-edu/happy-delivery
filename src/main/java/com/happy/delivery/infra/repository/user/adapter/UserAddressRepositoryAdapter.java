package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.infra.mybatis.user.UserAddressMapper;
import java.util.List;

/**
 * UserAddressRepositoryAdapter.
 */
public class UserAddressRepositoryAdapter implements UserAddressRepository {

  private final UserAddressMapper userAddressMapper;

  public UserAddressRepositoryAdapter(UserAddressMapper userAddressMapper) {
    this.userAddressMapper = userAddressMapper;
  }

  /**
   * save.
   * 주소 저장 및 변경.
   */
  @Override
  public UserAddress save(UserAddress userAddress) {
    if ((userAddress.getId() == null) || (userAddress.getId() <= 0L)) {
      userAddressMapper.insert(userAddress);
    } else {
      userAddressMapper.update(userAddress);
    }
    return userAddress;
  }

  /**
   * findById.
   * 식별자를 통해 주소 검색.
   */
  @Override
  public UserAddress findById(Long id) {
    return userAddressMapper.findById(id);
  }

  /**
   * findAllByUserId.
   * 회원 식별자를 통해 주소 검색.
   */
  @Override
  public List<UserAddress> findAllByUserId(Long userId) {
    return userAddressMapper.findAllByUserId(userId);
  }

  /**
   * findByUserIdAndMainAddressIsTrue.
   * 현재 주소로 지정된 주소 가져오는 메서드.
   */
  @Override
  public UserAddress findByUserIdAndMainAddressIsTrue(Long userId) {
    return userAddressMapper.findByUserIdAndMainAddressIsTrue(userId);
  }

  /**
   * deleteById.
   * 식별자로 주소 삭제.
   */
  @Override
  public void deleteById(Long id) {
    userAddressMapper.deleteById(id);
  }
}
