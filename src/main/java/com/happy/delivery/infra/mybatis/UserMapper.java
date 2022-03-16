package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper.
 */
@Mapper
public interface UserMapper {
  public void insert(User user);

  public User findById(Long id);

  public User findByEmail(String email);

  public void deleteUser(Long id);
}
