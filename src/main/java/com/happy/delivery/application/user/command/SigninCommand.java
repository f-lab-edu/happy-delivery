package com.happy.delivery.application.user.command;

import com.happy.delivery.domain.user.User;

public class SigninCommand {
    private String email;
    private String password;

    public SigninCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toUser() {
        return new User(
                this.email,
                this.password,
                null,
                null,
                null
        );
    }
}
