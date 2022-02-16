package com.happy.delivery.domain.exception.user;

public class UserAlreadyExistedException extends RuntimeException {
    public UserAlreadyExistedException() {
    }

    public UserAlreadyExistedException(String message) {
        super(message);
    }
}
