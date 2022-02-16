package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserResult;

public interface UserService {
    public UserResult signup(SignupCommand signCommand);

    public UserResult signin(SigninCommand signinCommand);
}
