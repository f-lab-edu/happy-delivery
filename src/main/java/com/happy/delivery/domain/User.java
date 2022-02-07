package com.happy.delivery.domain;

public class User {

        private String id;
        private String password;
        private String phoneNumber;
        private String email;
        private String address;

        public User(String id, String password, String phoneNumber, String email, String address) {
                this.id = id;
                this.password = password;
                this.phoneNumber = phoneNumber;
                this.email = email;
                this.address = address;
        }


}
