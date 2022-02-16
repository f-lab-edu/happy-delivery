package com.happy.delivery.domain.exception.user;

public class PasswordIsNotMatchException extends RuntimeException {
    public PasswordIsNotMatchException() {
    }

    public PasswordIsNotMatchException(String message) {
        super(message);
    }
}
