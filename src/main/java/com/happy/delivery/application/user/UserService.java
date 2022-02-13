package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.domain.exception.EmailAlreadyUserException;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    public SignupResult signup(SignupCommand signCommand) throws EmailAlreadyUserException, NoSuchAlgorithmException;

    public void signin(SigninCommand signinCommand);
}
