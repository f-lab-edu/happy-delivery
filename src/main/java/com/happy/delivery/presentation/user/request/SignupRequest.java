package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.domain.enumeration.Authority;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * SignupRequest.
 */
public class SignupRequest {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
      message = "최소 8 자, 최소 하나의 문자 및 하나의 숫자")
  private String password;

  @NotBlank
  @Pattern(regexp = "^[가-힣]*$", message = "이름을 한글로 입력해주세요.")
  @Size(min = 2, max = 8)
  private String name;

  @NotBlank
  @Pattern(regexp = "^\\d{11}$", message = "'-'없이 숫자 11자리만 입력해주세요.")
  private String phoneNumber;

  @NotNull
  private Authority authority;

  /**
   * SignupRequest Constructor.
   */
  public SignupRequest(String email, String password, String name, String phoneNumber,
      Authority authority) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  /**
   * SignupRequest ->SignCommand.
   */
  public SignupCommand toCommand() {
    return new SignupCommand(
        this.email,
        this.password,
        this.name,
        this.phoneNumber,
        this.authority
    );
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Authority getAuthority() {
    return authority;
  }
}
