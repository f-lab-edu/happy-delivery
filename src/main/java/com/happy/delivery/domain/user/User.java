package com.happy.delivery.domain.user;

public class User {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;


    public User(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password, String name, String phoneNumber, Long id) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
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

    public Long id() {
        return id;
    }

}

