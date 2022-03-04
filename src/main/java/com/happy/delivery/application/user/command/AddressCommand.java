package com.happy.delivery.application.user.command;

import org.springframework.lang.Nullable;

/**
 * AddressCommand.
 */
public class AddressCommand {

  private Long addressId;

  private final Long userId;

  private final String addressCode;

  @Nullable
  private final String addressDetail;

  /**
   * AddressCommand Constructor.
   */
  public AddressCommand(Long addressId, Long userId, String addressCode,
      @Nullable String addressDetail) {
    this.addressId = addressId;
    this.userId = userId;
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  public Long getAddressId() {
    return addressId;
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
