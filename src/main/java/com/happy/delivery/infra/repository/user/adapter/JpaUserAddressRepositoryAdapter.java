package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.infra.jpa.user.JpaUserAddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * JpaUserAddressRepositoryAdapter.
 */
@Repository
public class JpaUserAddressRepositoryAdapter implements UserAddressRepository {

  private final JpaUserAddressRepository jpaUserAddressRepository;

  @Autowired
  public JpaUserAddressRepositoryAdapter(JpaUserAddressRepository jpaUserAddressRepository) {
    this.jpaUserAddressRepository = jpaUserAddressRepository;
  }

  /**
   * save.
   * 주소 저장 및 변경.
   */
  @Override
  public UserAddress save(UserAddress userAddress) {
    return jpaUserAddressRepository.save(userAddress);
  }

  /**
   * findById.
   * 식별자를 통해 주소 검색.
   */
  @Override
  public UserAddress findById(Long id) {
    return jpaUserAddressRepository.findById(id).orElse(null);
  }

  /**
   * findAllByUserId.
   * 회원 식별자를 통해 주소 검색.
   */
  @Override
  public List<UserAddress> findAllByUserId(Long userId) {
    return jpaUserAddressRepository.findAllByUserId(userId);
  }

  /**
   * findByUserIdAndMainAddressIsTrue.
   * 현재 주소로 지정된 주소 가져오는 메서드.
   */
  @Override
  public UserAddress findByUserIdAndMainAddressIsTrue(Long userId) {
    return jpaUserAddressRepository.findByUserIdAndMainAddressIsTrue(userId);
  }

  /**
   * deleteById.
   * 식별자로 주소 삭제.
   */
  @Override
  public void deleteById(Long id) {
    jpaUserAddressRepository.deleteById(id);
  }
}
