package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.User;

public class SignupResult {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignupResult(){}

    public SignupResult(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public SignupResult toSignupResult(User user) {
        return new SignupResult(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getPhoneNumber()
        );
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


}
