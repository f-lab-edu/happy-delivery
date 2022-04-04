package com.happy.delivery.infra.mybatis.user;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserAddressMapper.
 */
@Mapper
public interface UserAddressMapper {

  void insert(UserAddress userAddress);

  void update(UserAddress userAddress);

  UserAddress findById(Long id);

  List<UserAddress> findAllByUserId(Long userId);

  boolean deleteById(Long id);

  boolean deleteAllByUserId(Long userId);
}
