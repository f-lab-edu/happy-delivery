package com.happy.delivery.domain.user;

import com.happy.delivery.application.user.result.SignupResult;

public class User {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public User(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public SignupResult toSignupResult() {
        return new SignupResult(
                this.email,
                this.password,
                this.name,
                this.phoneNumber
        );
    }
}

