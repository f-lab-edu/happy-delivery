package com.happy.delivery.application.command;

import com.happy.delivery.application.result.SignupResult;

public class SignupCommand {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignupCommand(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public SignupResult fromSignResponse(SignupCommand signCommand) {
        SignupResult signResponse = new SignupResult(
                signCommand.getEmail(),
                signCommand.getPassword(),
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );
        return signResponse;
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
