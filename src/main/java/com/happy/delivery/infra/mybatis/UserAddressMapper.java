package com.happy.delivery.infra.mybatis;

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

  int deleteById(Long id);
}
