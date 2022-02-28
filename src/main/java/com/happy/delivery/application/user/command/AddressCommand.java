package com.happy.delivery.application.user.command;

import org.springframework.lang.Nullable;

/**
 * AddressCommand.
 */
public class AddressCommand {

  private final Long userId;

  private final String addressCode;

  @Nullable
  private final String addressDetail;

  /**
   * AddressCommand Constructor.
   */
  public AddressCommand(Long userId, String addressCode, @Nullable String addressDetail) {
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  public Long getUserId() {
    return userId;
  }

  public String getAddressCode() {
    return addressCode;
  }

  @Nullable
  public String getAddressDetail() {
    return addressDetail;
  }
}
