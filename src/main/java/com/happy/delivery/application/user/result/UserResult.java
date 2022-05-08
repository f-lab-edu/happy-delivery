package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.enumeration.Authority;
import com.happy.delivery.domain.user.User;

/**
 * UserResult.
 */
public class UserResult {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;
  private Authority authority;
  private String token; // Redis 를 위한 field, Constructor 에서 사용하지 않음.

  /**
   * UserResult Constructor.
   */
  public UserResult(Long id, String email, String name, String phoneNumber, Authority authority) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  /**
   * UserResult -> User.
   */
  public static UserResult fromUser(User user) {
    return new UserResult(
        user.getId(),
        user.getEmail(),
        user.getName(),
        user.getPhoneNumber(),
        user.getAuthority()
    );
  }

  /**
   * addCurrentUserToken.
   * UUID 로 만든 토큰값을 UserResult 에 넣어주는 메서드.
   * response 로 토큰값을 보내기 위해 사용하는 메서드.
   */
  public void addCurrentUserToken(String currentToken) {
    this.token = currentToken;
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

  public Authority getAuthority() {
    return authority;
  }

  public String getToken() {
    return token;
  }

  @Override
  public String toString() {
    return "UserResult{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", authority=" + authority +
        ", token='" + token + '\'' +
        '}';
  }
}
