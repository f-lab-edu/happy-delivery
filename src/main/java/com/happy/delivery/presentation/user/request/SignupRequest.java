package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.command.SignupCommand;

//dto
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignupRequest(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public SignupCommand toCommand(SignupRequest signupRequest) {
        SignupCommand signCommand = new SignupCommand(
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getName(),
                signupRequest.getPhoneNumber()
        );
        return signCommand;
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
