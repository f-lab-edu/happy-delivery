package com.happy.delivery.infra.mybatis;

import com.happy.delivery.domain.user.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
  List<Menu> MenuSelect(Long storeId);
}
