package com.happy.delivery.application.user.result;

import com.happy.delivery.domain.user.User;

public class UserResult {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;

    public UserResult(Long id, String email, String name, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static UserResult fromUser(User user) {
        return new UserResult(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber()
        );
    }

    public Long getId() {
        return id;
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
