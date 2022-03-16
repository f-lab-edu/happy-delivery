package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

/**
 * UserRepository.
 */
public interface UserRepository {

  public void insert(User user);

  public User findById(Long id);

  public User findByEmail(String email);

  public void deleteUser(Long id);
}
