package com.happy.delivery.domain.ceo.repository;


import com.happy.delivery.domain.ceo.Ceo;
import com.happy.delivery.domain.user.User;

/**
 * CeoRepository.
 */
public interface CeoRepository {

  Ceo ceoSave(Ceo ceo);

  Ceo findByCeoEmail(String email);
}
