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

  /**
   * save.
   * 주소 저장 및 변경.
   */
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
  public Optional<UserAddress> findById(Long id) {
    UserAddress userAddress = userAddressMapper.findById(id);
    return Optional.ofNullable(userAddress);
  }

  /**
   * findByUserId.
   * 회원 식별자를 통해 주소 검색.
   */
  public List<UserAddress> findByUserId(Long userId) {
    return userAddressMapper.findByUserId(userId);
  }

  /**
   * findByUserIdAndMainAddressIsTrue.
   * 현재 주소로 지정된 주소 가져오는 메서드.
   */
  public UserAddress findByUserIdAndMainAddressIsTrue(Long userId) {
    return userAddressMapper.findByUserIdAndMainAddressIsTrue(userId);
  }

  /**
   * deleteById.
   * 식별자로 주소 삭제.
   */
  public void deleteById(Long id) {
    userAddressMapper.deleteById(id);
  }
}
