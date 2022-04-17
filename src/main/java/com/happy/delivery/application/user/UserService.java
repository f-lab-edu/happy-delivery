package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import java.util.List;

/**
 * UserService.
 */
public interface UserService {

  UserResult signup(SignupCommand signCommand);

  UserResult signin(SigninCommand signinCommand);

  UserResult getMyAccount(Long userId);

  UserResult updateMyAccount(MyAccountCommand myAccountCommand);

  UserResult updatePassword(Long userId, PasswordUpdateCommand passwordUpdateCommand);

  void deleteMyAccount(Long userId);

  UserAddressResult saveAddress(Long userId, AddressCommand addressCommand);

  List<UserAddressResult> getListOfAllAddresses(Long userId);

  UserAddressResult updateMainAddress(Long userId, Long addressId);

  UserAddressResult updateAddress(Long addressId, Long userId, AddressCommand addressCommand);

  void deleteAddress(Long addressId, Long userId);
}
