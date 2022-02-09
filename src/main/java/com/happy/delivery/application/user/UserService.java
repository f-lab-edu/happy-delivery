package com.happy.delivery.application.user;

import com.happy.delivery.application.command.SignCommand;
import com.happy.delivery.application.response.SignResponse;
import com.happy.delivery.domain.User;
import com.happy.delivery.domain.UserRepository;
import com.happy.delivery.presentation.user.request.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignResponse signup(SignCommand signCommand) {
        User user = new User(
                signCommand.getEmail(),
                signCommand.getPassword(),
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );
        userRepository.save(user);
        SignResponse signResponse = signCommand.fromSignResponse(signCommand);
        return signResponse;
    }

}
