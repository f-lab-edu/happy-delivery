package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.user.User;

public interface UserService {
    public UserResult signup(SignupCommand signCommand);
    public UserResult signin(SigninCommand signinCommand);

}
