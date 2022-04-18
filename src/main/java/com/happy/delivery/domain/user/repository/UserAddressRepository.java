package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserAddressRepository.
 */
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

  UserAddress save(UserAddress userAddress);

  Optional<UserAddress> findById(Long id);

  List<UserAddress> findByUserId(Long userId);

  UserAddress findByUserIdAndMainAddressIsTrue(Long userId);

  void deleteById(Long id);
}
