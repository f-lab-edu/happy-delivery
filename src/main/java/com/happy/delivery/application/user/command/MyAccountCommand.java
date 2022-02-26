package com.happy.delivery.application.user.command;

/**
 * MyAccountCommand.
 */
public class MyAccountCommand {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;

  /**
   * MyAccountCommand Constructor.
   */
  public MyAccountCommand(Long id, String email, String name, String phoneNumber) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
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
}
