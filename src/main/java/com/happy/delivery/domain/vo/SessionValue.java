package com.happy.delivery.domain.vo;

import com.happy.delivery.domain.enumeration.Authority;
import java.util.Objects;

/**
 * SessionValue.
 * session 에 저장해야 하는 값들을 모아 둔 VO.
 */
public class SessionValue {

  private final Long memberId;
  private final Authority authority;

  /**
   * SessionValue constructor.
   */
  public SessionValue(Long memberId, Authority authority) {
    this.memberId = memberId;
    this.authority = authority;
  }

  public Long getMemberId() {
    return memberId;
  }

  public Authority getAuthority() {
    return authority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionValue that = (SessionValue) o;
    return memberId.equals(that.memberId) && authority == that.authority;
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, authority);
  }
}
