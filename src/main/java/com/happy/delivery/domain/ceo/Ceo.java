package com.happy.delivery.domain.ceo;


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

  public Ceo(String email, String password) {
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

  @Override
  public String toString() {
    return "Ceo{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
