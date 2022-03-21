package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.mybatis.UserMapper;

/**
 * UserRepositoryAdapter.
 */
public class UserRepositoryAdapter implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryAdapter(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User save(User user) {
    if (user.getId() == null || user.getId() <= 0L) {
      userMapper.insert(user);
    }
    userMapper.update(user);
    return user;
  }

  @Override
  public User findById(Long id) {
    return userMapper.findById(id);
  }

  @Override
  public User findByEmail(String email) {
    return userMapper.findByEmail(email);
  }

  @Override
  public boolean deleteId(Long id) {
    return userMapper.deleteId(id);
  }
}