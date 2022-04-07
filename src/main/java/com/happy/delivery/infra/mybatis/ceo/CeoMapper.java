package com.happy.delivery.infra.mybatis.ceo;

import com.happy.delivery.domain.ceo.Ceo;
import org.apache.ibatis.annotations.Mapper;

/**
 * CeoMapper.
 */
@Mapper
public interface CeoMapper {

  Ceo findByCeoEmail(String email);

  void ceoInsert(Ceo ceo);
}
