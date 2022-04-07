package com.happy.delivery.domain.ceo.repository;


/**
 * Ceo domain.
 */
public class Ceo {
  private Long id;
  private String email;
  private String password;

  /**
   * Ceo domain constructor.
   */
  public Ceo(Long id, String email, String password) {
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
