package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.PasswordUpdateCommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PasswordUpdateRequest {

    @NotNull
    private final Long id;

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "최소 8 자, 최소 하나의 문자 및 하나의 숫자")
    private final String currentPassword;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "최소 8 자, 최소 하나의 문자 및 하나의 숫자")
    private final String password;

    public PasswordUpdateRequest(Long id, String currentPassword, String password) {
        this.id = id;
        this.currentPassword = currentPassword;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public PasswordUpdateCommand toCommand(){
        return new PasswordUpdateCommand(
                this.id,
                this.currentPassword,
                this.password
        );
    }

    @Override
    public String toString() {
        return "PasswordUpdateRequest{" +
                "id=" + id +
                ", currentPassword='" + currentPassword + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
