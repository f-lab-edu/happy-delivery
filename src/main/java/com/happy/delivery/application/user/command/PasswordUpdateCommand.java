package com.happy.delivery.application.user.command;

public class PasswordUpdateCommand {
    private final Long id;
    private final String currentPassword;
    private final String password;

    public PasswordUpdateCommand(Long id, String currentPassword, String password) {
        this.id = id;
        this.currentPassword = currentPassword;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordUpdateCommand{" +
                "id=" + id +
                ", currentPassword='" + currentPassword + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
