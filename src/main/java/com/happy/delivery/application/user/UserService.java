package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;

/**
 * UserService.
 */
public interface UserService {

  public UserResult signup(SignupCommand signCommand);

  public UserResult signin(SigninCommand signinCommand);

  public UserResult updatePassword(Long id, PasswordUpdateCommand passwordUpdateCommand);

  public UserAddressResult saveAddress(AddressCommand address);
}
