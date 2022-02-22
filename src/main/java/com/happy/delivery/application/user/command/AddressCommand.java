package com.happy.delivery.application.user.command;

import org.springframework.lang.Nullable;

/**
 * AddressCommand.
 */
public class AddressCommand {

  private String addressCode;

  @Nullable
  private String addressDetail;

  public AddressCommand(String addressCode, String addressDetail) {
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  /**
   * makeTotalAddress.
   */
  public String makeTotalAddress() {
    if (addressDetail == null) {
      return addressCode;
    }
    return addressCode + addressDetail;
  }
}
