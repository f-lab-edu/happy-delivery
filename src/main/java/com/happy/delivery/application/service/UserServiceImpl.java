package com.happy.delivery.application.service;

import com.happy.delivery.application.dto.UserDTO;
import com.happy.delivery.domain.User;
import com.happy.delivery.infra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signup(UserDTO userDTO) {
       User user = new User(
               userDTO.getId(),
               userDTO.getPassword(),
               userDTO.getPhoneNumber(),
               userDTO.getEmail(),
               userDTO.getAddress()
       );
       userRepository.save(user);
       log.info("userDTO = {}", userDTO);
    }
}
