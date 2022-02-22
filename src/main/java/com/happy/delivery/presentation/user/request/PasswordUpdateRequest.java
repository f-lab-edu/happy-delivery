package com.happy.delivery.presentation.user.request;

import com.happy.delivery.application.user.command.PasswordUpdateCommand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PasswordUpdateRequest {

    @NotBlank(message = "현재 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "최소 8 자, 최소 하나의 문자 및 하나의 숫자")
    private final String currentPassword;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "최소 8 자, 최소 하나의 문자 및 하나의 숫자")
    private final String changedPassword;

    public PasswordUpdateRequest(String currentPassword, String changedPassword) {
        this.currentPassword = currentPassword;
        this.changedPassword = changedPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public PasswordUpdateCommand toCommand(){
        return new PasswordUpdateCommand(
            this.currentPassword,
            this.changedPassword
        );
    }

    @Override
    public String toString() {
        return "PasswordUpdateRequest{" +
            ", currentPassword='" + currentPassword + '\'' +
            ", changedPassword='" + changedPassword + '\'' +
            '}';
    }
}
