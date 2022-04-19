package com.happy.delivery.infra.jpa.user;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaUserAddressRepository.
 */
public interface JpaUserAddressRepository extends JpaRepository<UserAddress, Long> {

  UserAddress save(UserAddress userAddress);

  Optional<UserAddress> findById(Long id);

  List<UserAddress> findAllByUserId(Long userId);

  UserAddress findByUserIdAndMainAddressIsTrue(Long userId);

  void deleteById(Long id);
}
