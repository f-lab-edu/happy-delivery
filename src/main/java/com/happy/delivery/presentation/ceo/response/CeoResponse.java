package com.happy.delivery.presentation.ceo.response;

/**
 * CeoResponse.
 */
public class CeoResponse {
  private Long id;
  private String email;
  private String password;


  /**
   * CeoResponse Constructor.
   */
  public CeoResponse(Long id, String email, String password, String name,
      String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
