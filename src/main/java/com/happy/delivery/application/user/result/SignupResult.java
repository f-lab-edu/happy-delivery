package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.User;

public class SignupResult {
    private String email;
    private String name;
    private String phoneNumber;

    public SignupResult(String email, String name, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static SignupResult fromUser(User user) {
        return new SignupResult(
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber()
        );
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
