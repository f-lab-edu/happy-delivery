package com.happy.delivery.application.service;

import com.happy.delivery.application.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void signup(UserDTO userDTO) {
        System.out.println("회원가입 완료");
    }
}
