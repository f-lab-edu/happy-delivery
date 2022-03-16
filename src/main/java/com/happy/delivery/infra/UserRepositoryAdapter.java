package com.happy.delivery.infra;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.mybatis.UserMapper;

public class UserRepositoryAdapter implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryAdapter(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public void insert(User user) {
    userMapper.insert(user);
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
  public void deleteUser(Long id) {
    userMapper.deleteUser(id);
  }
}
