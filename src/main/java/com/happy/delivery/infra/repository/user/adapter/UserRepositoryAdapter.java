package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.mybatis.user.UserMapper;

/**
 * UserRepositoryAdapter.
 */
public class UserRepositoryAdapter implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryAdapter(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  /**
   * save.
   * 주소 저장 및 수정.
   */
  public User save(User user) {
    if (user.getId() == null || user.getId() <= 0L) {
      userMapper.insert(user);
      return user;
    }
    userMapper.update(user);
    return user;
  }

  /**
   * findById.
   * 식별자로 값 찾기.
   */
  public User findById(Long id) {
    return userMapper.findById(id);
  }

  /**
   * findByEmail.
   * 이메일로 값 찾기.
   */
  public User findByEmail(String email) {
    return userMapper.findByEmail(email);
  }

  /**
   * deleteById.
   * 식별자로 삭제하기.
   */
  public void deleteById(Long id) {
    userMapper.deleteById(id);
  }
}
