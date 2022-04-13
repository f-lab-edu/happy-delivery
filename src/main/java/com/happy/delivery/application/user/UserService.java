package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SaveAddressCommand;
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

  UserResult getMyAccount(Long loginId);

  UserResult updateMyAccount(MyAccountCommand myAccountCommand);

  UserResult updatePassword(Long id, PasswordUpdateCommand passwordUpdateCommand);

  boolean deleteMyAccount(Long loinid);

  UserAddressResult saveAddress(Long userId, SaveAddressCommand saveAddressCommand);

  UserResult setMainAddress(Long userId, Long addressId);

  List<UserAddressResult> getListOfAllAddresses(Long loginId);

  UserAddressResult updateAddress(AddressCommand addressCommand);

  boolean deleteAddress(AddressCommand addressCommand);
}
