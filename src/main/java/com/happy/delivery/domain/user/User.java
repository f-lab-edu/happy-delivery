package com.happy.delivery.domain.user;

import com.happy.delivery.domain.enumeration.Authority;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * User.
 */
@Entity(name = "User")
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String name;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "authority")
  private Authority authority;

  /**
   * 기본 생성자.
   */
  public User() {
  }

  /**
   * 회원가입 할 때 사용.
   */
  public User(String email, String password, String name, String phoneNumber, Authority authority) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
  }

  /**
   * 보통의 경우.
   */
  public User(Long id, String email, String password, String name, String phoneNumber,
      Long addressId, Authority authority) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.authority = authority;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Authority getAuthority() {
    return authority;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", authority=" + authority +
        '}';
  }
}

