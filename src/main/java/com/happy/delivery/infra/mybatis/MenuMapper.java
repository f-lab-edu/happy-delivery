package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * MenuMapper.
 */
@Mapper
public interface MenuMapper {
  List<Menu> getAllByStoreId(Long storeId);
}
