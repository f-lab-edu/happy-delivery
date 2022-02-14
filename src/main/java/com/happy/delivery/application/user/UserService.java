package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.domain.exception.user.EmailAlreadyUserException;

public interface UserService {
    public SignupResult signup(SignupCommand signCommand) throws EmailAlreadyUserException;

    public String signin(SigninCommand signinCommand);

}
