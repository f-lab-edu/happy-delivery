package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;

/**
 * UserAddressRepository.
 */
public interface UserAddressRepository {

  UserAddress save(UserAddress userAddress);

  UserAddress findById(Long id);

  List<UserAddress> findAllByUserId(Long userId);

  int deleteById(Long id);
}
