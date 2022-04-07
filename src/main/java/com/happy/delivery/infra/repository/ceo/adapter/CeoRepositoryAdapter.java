package com.happy.delivery.infra.repository.ceo;

import com.happy.delivery.domain.ceo.Ceo;
import com.happy.delivery.domain.ceo.repository.CeoRepository;
import com.happy.delivery.infra.mybatis.ceo.CeoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CeoRepositoryAdapter implements CeoRepository {
  private final CeoMapper ceoMapper;

  @Autowired
  public CeoRepositoryAdapter(CeoMapper ceoMapper) {
    this.ceoMapper = ceoMapper;
  }

  @Override
  public Ceo findByCeoEmail(String email) {
    return ceoMapper.findByCeoEmail(email);
  }
}
