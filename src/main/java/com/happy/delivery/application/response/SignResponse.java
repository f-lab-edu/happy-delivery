package com.happy.delivery.application.response;

import com.happy.delivery.application.command.SignCommand;
import com.happy.delivery.domain.User;

public class SignResponse {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignResponse(String email, String password, String name, String phoneNumber) {
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
}
