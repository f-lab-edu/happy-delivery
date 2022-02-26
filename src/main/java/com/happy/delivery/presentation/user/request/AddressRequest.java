package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.AddressCommand;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.lang.Nullable;

/**
 * AddressRequest.
 */
public class AddressRequest {

  //우편주소
  @NotBlank(message = "주소를 입력해주세요.")
  @Pattern(regexp = "^[가-힣0-9\\s]*$", message = "형식이 맞지 않습니다. 한글과 숫자만 입력해주세요.")
  private String addressCode;

  // 상세 주소(xxx호)
  @Nullable
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣0-9\\s]*$", message = "형식이 맞지 않습니다. 한글과 숫자만 입력해주세요.")
  private String addressDetail;

  public AddressRequest(String addressCode, String addressDetail) {
    this.addressCode = addressCode;
    this.addressDetail = addressDetail;
  }

  /**
   * AddressRequest --> AddressCommand.
   */
  public AddressCommand toCommand(Long userId) {
    return new AddressCommand(
        userId,
        this.addressCode,
        this.addressDetail
    );
  }
}
