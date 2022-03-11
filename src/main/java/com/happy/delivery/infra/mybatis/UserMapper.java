package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper.
 */
@Mapper
public interface UserMapper {
  public void save(User user);

  public User findById(Long id);

  public User findByEmail(String email);

  public User deleteUser(Long id);
}
