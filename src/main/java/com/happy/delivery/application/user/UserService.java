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

  UserResult updateMyAccount(MyAccountCommand myAccountCommand);

  boolean deleteMyAccount(Long loinid);

  UserResult getMyAccount(Long loginId);

  UserResult updatePassword(Long id, PasswordUpdateCommand passwordUpdateCommand);

  UserAddressResult saveAddress(AddressCommand address);

  UserResult setMainAddress(Long userId, Long addressId);

  List<UserAddressResult> getListOfAllAddresses(Long loginId);

  UserAddressResult updateAddress(AddressCommand addressCommand);

  boolean deleteAddress(AddressCommand addressCommand);
}
