package com.happy.delivery.domain;

public class User {

    private String eamil;
    private String password;
    private String name;
    private String phoneNumber;

    public User(String eamil, String password, String name, String phoneNumber) {
        this.eamil = eamil;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getEamil() {
        return eamil;
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

