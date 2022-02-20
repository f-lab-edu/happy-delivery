package com.happy.delivery.application.user.command;

public class PasswordUpdateCommand {
    private final String currentPassword;
    private final String changedPassword;

    public PasswordUpdateCommand(String currentPassword, String changedPassword) {
        this.currentPassword = currentPassword;
        this.changedPassword = changedPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    @Override
    public String toString() {
        return "PasswordUpdateCommand{" +
                ", currentPassword='" + currentPassword + '\'' +
                ", changedPassword='" + changedPassword + '\'' +
                '}';
    }
}
