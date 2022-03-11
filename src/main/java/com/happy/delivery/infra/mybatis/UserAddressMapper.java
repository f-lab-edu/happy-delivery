package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.UserAddress;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserAddressMapper.
 */
@Mapper
public interface UserAddressMapper {

  public void save(UserAddress userAddress);

  public void update(UserAddress userAddress);

  public UserAddress findById(Long id);

  public List<UserAddress> findAllByUserId(Long userId);

  public void deleteById(Long id);
}
