package com.happy.delivery.application.user.command;

import java.util.Optional;
import javax.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

public class AddressCommand {
  private String addressCode;

  @Nullable
  private String addressDetail;

  public AddressCommand(String addressCode, String addressDetail) {
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  public String makeTotalAddress() {
    if (addressDetail == null) return addressCode;
    return addressCode + addressDetail;
  }
}
