package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.SigninCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SigninRequest {

    private final Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public SigninRequest(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public SigninCommand toCommand() {
        return new SigninCommand(
                this.email,
                this.password
        );
    }
}
