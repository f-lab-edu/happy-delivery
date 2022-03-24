package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper.
 */
@Mapper
public interface UserMapper {

  void insert(User user);

  void update(User user);

  User findById(Long id);

  User findByEmail(String email);

  boolean deleteId(Long id);

}
