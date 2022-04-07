package com.happy.delivery.application.ceo.result;

import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.ceo.Ceo;

/**
 * CeoResult.
 */
public class CeoResult {

  private Long id;
  private String email;

  /**
   * CeoResult Constructor.
   */
  public CeoResult(Long id, String email) {
    this.id = id;
    this.email = email;
  }

  /**
   * CeoResult from Ceo.
   */
  public static CeoResult fromCeo(Ceo ceo) {
    return new CeoResult(
        ceo.getId(),
        ceo.getEmail()
    );
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

}
