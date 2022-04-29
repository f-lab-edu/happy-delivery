package com.happy.delivery.application.ceo.command;

/**
 * CeoCommand.
 */
public class CeoSigninCommand {

  private Long id;
  private String email;
  private String password;

  /**
   * CeoSigninCommand Constructor.
   */
  public CeoSigninCommand(Long id, String email, String password) {
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
