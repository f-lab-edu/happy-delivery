package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.MyAccountCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * MyAccountRequest.
 */
public class MyAccountRequest {

  private Long id;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Pattern(regexp = "^[가-힣]*$", message = "이름을 한글로 입력해주세요.")
  @Size(min = 2, max = 8)
  private String name;

  @NotBlank
  @Pattern(regexp = "^\\d{11}$", message = "'-'없이 숫자 11자리만 입력해주세요.")
  private String phoneNumber;

  /**
   * MyAccountRequest Constructor.
   */
  public MyAccountRequest(Long id, String email, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * MyAccountRequest -> MyAccountCommand.
   */
  public MyAccountCommand toCommand() {
    return new MyAccountCommand(
        this.id,
        this.email,
        this.name,
        this.phoneNumber
    );
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * addSessionLoginId.
   * session에 들어있는 회원 식별자 넣어주는 메서드.
   */
  public void addSessionLoginId(Long sessionLoginId) {
    this. id = sessionLoginId;
  }
}
