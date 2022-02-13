package com.happy.delivery.application.user.command;

import com.happy.delivery.application.user.result.SignupResult;
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

    //이게 맞는 방식인가? 전혀 아닌 것 같음.
    public User toUser() {
        return new User(
                this.email,
                this.password,
                null,
                null,
                null
        );
    }

    @Override
    public String toString() {
        return "SigninCommand{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
