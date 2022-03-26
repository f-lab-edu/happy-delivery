package com.happy.delivery.domain.user;

/**
 * User.
 */
public class User {

  private Long id;
  private String email;
  private String password;
  private String name;
  private String phoneNumber;
  private Long addressId;

  /**
   * 회원가입 할 때 사용.
   */
  public User(String email, String password, String name, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * 보통의 경우.
   */
  public User(Long id, String email, String password, String name, String phoneNumber,
      Long addressId) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.addressId = addressId;
  }

  /**
   * 유저 정보 업데이트.
   */
  public void setMyAccountUpdate(String email, String name, String phoneNumber) {
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * changePassword(). setter + encoder.
   *
   * <p> 생각해보기!
   * 멘토님의 말씀 :: domain에서 infra의 의존성을 바로 가지는건 좋지 않을 것 같네요. 패스워드를 암호화 하는게 도메인의 역할이라 생각된다면 domain에서
   * 패스워드를 암호화하는 인터페이스를 구현해두고, infra에서 구현한 뒤 주입받아서 사용하여야 될 것 같네요.</p>
   */
  public void changePassword(String changedPassword) {
    this.password = changedPassword;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", addressId=" + addressId +
        '}';
  }
}

