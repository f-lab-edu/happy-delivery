package com.happy.delivery.application.user;

import com.happy.delivery.application.command.SignCommand;
import com.happy.delivery.application.response.SignResponse;

public interface UserService {
    public SignResponse signup(SignCommand signCommand);
}
