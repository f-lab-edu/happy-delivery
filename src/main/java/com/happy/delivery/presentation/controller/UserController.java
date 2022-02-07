package com.happy.delivery.presentation.controller;
import com.happy.delivery.application.dto.UserDTO;
import com.happy.delivery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getAddress());
        userService.signup(userDTO);
    }
}
