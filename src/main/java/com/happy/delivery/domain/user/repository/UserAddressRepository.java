package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;

/**
 * UserAddressRepository.
 */
public interface UserAddressRepository {

  public UserAddress save(UserAddress userAddress);

  public UserAddress findById(Long id);

  public UserAddress findByLastUsedAddress(Long userId);

  public List<UserAddress> findAllByUserId(Long userId);
}
