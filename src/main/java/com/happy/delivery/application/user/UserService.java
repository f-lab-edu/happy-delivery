package com.happy.delivery.application.user;

import com.happy.delivery.application.command.SignupCommand;
import com.happy.delivery.application.result.SignupResult;
import com.happy.delivery.domain.exception.EmailAlreadyUserException;

public interface UserService {
    public SignupResult signup(SignupCommand signCommand) throws EmailAlreadyUserException;
}
