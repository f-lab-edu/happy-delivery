package com.happy.delivery.application.service;

import com.happy.delivery.application.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    void signup(UserDTO userDTO);
}
