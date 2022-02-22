package com.happy.delivery.domain.user;

/**
 * User Domain.
 */
public class User {

  private Long id;
  private String email;
  private String password;
  private String name;
  private String phoneNumber;
  private String address;


  /**
   * User signin Constructor.
   */
  public User(String email, String password, String name, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * User not signin Constructor.
   */
  public User(Long id, String email, String password, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * ***** 주소 저장하는 경우 ***** :: ! 임시 !. 주소 저장 테이블을 따로 두고 싶음, 주소도 여러개 저장하고 싶음.
   */
  public User(Long id, String email, String password, String name, String phoneNumber,
      String address) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  ;

  public Long getId() {
    return id;
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

}

