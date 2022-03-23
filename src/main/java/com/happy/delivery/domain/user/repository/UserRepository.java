package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

/**
 * UserRepository.
 */
public interface UserRepository {

  User save(User user);

  User findById(Long id);

  User findByEmail(String email);

  boolean deleteId(Long id);

  User saveMainAddress(User user);
}
