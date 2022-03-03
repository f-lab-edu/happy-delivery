package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NoUserIdException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAddressNotExistedException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.presentation.user.request.AddressRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceV1.
 */
@Service
public class UserServiceV1 implements UserService {

  private final UserRepository userRepository;
  private final UserAddressRepository userAddressRepository;
  private final EncryptMapper encryptMapper;

  /**
   * UserServiceV1 Constructor.
   */
  @Autowired
  public UserServiceV1(UserRepository userRepository, UserAddressRepository userAddressRepository,
      EncryptMapper encryptMapper) {
    this.userRepository = userRepository;
    this.userAddressRepository = userAddressRepository;
    this.encryptMapper = encryptMapper;
  }

  @Override
  public UserResult signup(SignupCommand signCommand) {
    // email로 user 조회
    // null이 아니면, 이미 존재하는 계정이니 예외 발생
    if (userRepository.findByEmail(signCommand.getEmail()) != null) {
      throw new UserAlreadyExistedException("이미 존재하는 계정 입니다.");
    }
    // password를 hash 암호화하여 repository에 저장
    User result = userRepository.save(
        new User(
            signCommand.getEmail(),
            encryptMapper.encoder(signCommand.getPassword()),
            // 패스워드 암호화 로직
            signCommand.getName(),
            signCommand.getPhoneNumber()));
    // 저장했다면 dto를 리턴하여 종료
    return UserResult.fromUser(result);
  }

  @Override
  public UserResult signin(SigninCommand signinCommand) {
    // 1. repo에 저장된 비밀번호 가져오기
    User user = userRepository.findByEmail(signinCommand.getEmail());
    if (user == null) {
      throw new EmailIsNotMatchException("이메일이 일치하지 않습니다."); //EmailIsNotMatch
    }
    // 2. 비밀번호 맞는지 확인
    if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("패스워드가 일치하지 않습니다."); //password
    }
    return UserResult.fromUser(user);
  }

  @Override
  public UserResult updateMyAccount(MyAccountCommand myAccountCommand) {
    if (myAccountCommand.getId() == null) {
      throw new NoUserIdException("유저 ID가 없습니다. 로그인 해주세요");
    }

    // 경우 1. user1, user2 생성, user1로그인 -> user2의 이메일로 변경 ; (변경안됨)
    // 경우 2. user1, user2 생성, user1로그인 -> user1의 이메일로 변경(그대로) 이름과 폰 번호만 변경 ; (변경됨)
    // 경우 3. user1, user2 생성, user1로그인 -> user3의 이메일 신규 생성 ; (변경됨)
    User byEmail = userRepository.findByEmail(myAccountCommand.getEmail());
    User user = userRepository.findById(myAccountCommand.getId());
    //repo에 값이 있거나 기존 이메일과 변경하려는 이메일이 같지 않은경우
    if (byEmail != null && !myAccountCommand.getEmail().equals(user.getEmail())) {
      throw new UserAlreadyExistedException("이미 존재하는 계정 입니다.");
    }
    user.setMyAccountUpdate(myAccountCommand.getEmail(), myAccountCommand.getName(),
        myAccountCommand.getPhoneNumber());
    User userSave = userRepository.save(user);
    return UserResult.fromUser(userSave);
  }

  @Override
  public void deleteMyAccount(Long loinId) {
    if (loinId == null) {
      throw new NoUserIdException("유저 ID가 없습니다. 로그인 해주세요");
    }
    userRepository.deleteUser(loinId);
  }

  @Override
  public UserResult getMyAccount(Long loginId) {
    if (loginId == null) {
      throw new NoUserIdException("유저 ID가 없습니다. 로그인 해주세요");
    }
    User user = userRepository.findById(loginId);
    return UserResult.fromUser(user);
  }

  /**
   * 비밀번호 변경
   * 1) 변경 전 비밀번호 일치여부 검사.
   * 2) 바꾸려는 비밀번호 암호화.
   * 3) User 비밀번호값 바꾸기 : changePassword
   * 4) repository 저장.
   */
  @Override
  public UserResult updatePassword(Long id, PasswordUpdateCommand passwordUpdateCommand) {
    User user = userRepository.findById(id);
    if (!encryptMapper.isMatch(passwordUpdateCommand.getCurrentPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("현재 패스워드가 일치하지 않습니다.");
    }
    user.changePassword(encryptMapper.encoder(passwordUpdateCommand.getChangedPassword()));
    User result = userRepository.save(user);
    return UserResult.fromUser(result);
  }

  @Override
  public UserAddressResult saveAddress(AddressCommand addressCommand) {
    UserAddress result = userAddressRepository.save(
        new UserAddress(
            addressCommand.getUserId(),
            addressCommand.getAddressCode(),
            addressCommand.getAddressDetail()));
    return UserAddressResult.fromUserAddress(result);
  }

  @Override
  public List<UserAddressResult> getListOfAllAddresses(Long loginId) {
    List<UserAddressResult> result = new ArrayList<>();
    List<UserAddress> addresses = userAddressRepository.findAllByUserId(loginId);
    for (UserAddress address : addresses) {
      result.add(UserAddressResult.fromUserAddress(address));
    }
    return result;
  }

  @Override
  public void updateAddress(Long addressId, AddressRequest addressRequest) {
    UserAddress userAddress = userAddressRepository.findById(addressId);
    if (userAddress == null) {
      throw new UserAddressNotExistedException("존재하지 않는 주소입니다.");
    }
    userAddress.changeAddress(addressRequest.getAddressCode(), addressRequest.getAddressDetail());
    userAddressRepository.save(userAddress);
  }

  @Override
  public UserAddressResult deleteAddress(Long addressId) {
    UserAddress result = userAddressRepository.deleteById(addressId);
    if (result == null) {
      throw new UserAddressNotExistedException("존재하지 않는 주소입니다.");
    }
    return UserAddressResult.fromUserAddress(result);
  }
}
