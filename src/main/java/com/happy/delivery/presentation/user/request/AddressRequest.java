package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.AddressCommand;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.lang.Nullable;

/**
 * AddressRequest.
 */
public class AddressRequest {

  private Double longitude;
  private Double latitude;

  /**
   * 주소.
   * 도로명주소와 지번주소 입력.
   */
  @NotBlank
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣0-9\\s]*$", message = "형식이 맞지 않습니다. 한글과 숫자만 입력해주세요.")
  private String addressDetail;

  /**
   * AddressRequest Constructor.
   */
  public AddressRequest(Double longitude, Double latitude, @Nullable String addressDetail) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.addressDetail = addressDetail;
  }

  /**
   * AddressRequest --> AddressCommand.
   */
  public AddressCommand toCommand() {
    return new AddressCommand(
        this.longitude,
        this.latitude,
        this.addressDetail
    );
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  @Nullable
  public String getAddressDetail() {
    return addressDetail;
  }
}
