package com.happy.delivery.application.command;

import com.happy.delivery.application.response.SignResponse;
import com.happy.delivery.presentation.user.request.SignupRequest;

public class SignCommand {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    public SignCommand(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public SignResponse fromSignResponse(SignCommand signCommand) {
        SignResponse signResponse = new SignResponse(
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
