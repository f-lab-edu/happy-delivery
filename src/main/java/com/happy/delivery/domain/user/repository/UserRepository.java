package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

/**
 * UserRepository.
 */
public interface UserRepository {

  public User save(User user);

  public User findById(Long id);

  public User findByEmail(String email);

  public boolean deleteId(Long id);
}
