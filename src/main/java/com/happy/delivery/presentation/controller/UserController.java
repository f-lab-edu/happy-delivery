package com.happy.delivery.presentation.controller;

import com.happy.delivery.application.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO userDTO) {

    }
}
