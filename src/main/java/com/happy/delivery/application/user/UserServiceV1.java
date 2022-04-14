package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SaveAddressCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.exception.user.CanNotDeleteMainAddressException;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NotAuthorizedException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAddressNotExistedException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.UserAddress;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.encoder.EncryptMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public UserResult signup(SignupCommand signCommand) {
    // email로 user 조회
    // null이 아니면, 이미 존재하는 계정이니 예외 발생
    if (userRepository.findByEmail(signCommand.getEmail()) != null) {
      throw new UserAlreadyExistedException("이미 존재하는 계정 입니다.");
    }

    User userResult = new User(
        signCommand.getEmail(),
        encryptMapper.encoder(signCommand.getPassword()),
        // 패스워드 암호화 로직
        signCommand.getName(),
        signCommand.getPhoneNumber()
    );
    userRepository.save(userResult);

    return UserResult.fromUser(userResult);
  }

  @Override
  @Transactional
  public UserResult signin(SigninCommand signinCommand) {
    // 1. repo에 저장된 비밀번호 가져오기
    User user = userRepository.findByEmail(signinCommand.getEmail());
    if (user == null) {
      throw new EmailIsNotMatchException("이메일이 일치하지 않습니다.");
    }
    // 2. 비밀번호 맞는지 확인
    if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("패스워드가 일치하지 않습니다.");
    }
    return UserResult.fromUser(user);
  }

  @Override
  @Transactional
  public UserResult getMyAccount(Long userId) {
    User user = userRepository.findById(userId);
    return UserResult.fromUser(user);
  }

  @Override
  @Transactional
  public UserResult updateMyAccount(MyAccountCommand myAccountCommand) {
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
    userRepository.save(user);
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
  @Transactional
  public UserResult updatePassword(Long userId, PasswordUpdateCommand passwordUpdateCommand) {
    User user = userRepository.findById(userId);
    if (!encryptMapper.isMatch(passwordUpdateCommand.getCurrentPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("현재 패스워드가 일치하지 않습니다.");
    }
    user.changePassword(encryptMapper.encoder(passwordUpdateCommand.getChangedPassword()));
    userRepository.save(user);
    return UserResult.fromUser(user);
  }

  @Override
  @Transactional
  public boolean deleteMyAccount(Long userId) {
    return userRepository.deleteId(userId);
  }

  @Override
  @Transactional
  public UserAddressResult saveAddress(Long userId, SaveAddressCommand saveAddressCommand) {
    makeCurrentMainAddressFalse(userId);
    UserAddress newAddress = userAddressRepository.save(
        new UserAddress(
            userId,
            saveAddressCommand.getLongitude(),
            saveAddressCommand.getLatitude(),
            saveAddressCommand.getAddressDetail(),
            true));
    return UserAddressResult.fromUserAddress(newAddress);
  }

  @Override
  @Transactional
  public List<UserAddressResult> getListOfAllAddresses(Long userId) {
    List<UserAddressResult> result = new ArrayList<>();
    List<UserAddress> addresses = userAddressRepository.findAllByUserId(userId);
    for (UserAddress address : addresses) {
      result.add(UserAddressResult.fromUserAddress(address));
    }
    return result;
  }

  /**
   * <p>현재 주소 변경하기.
   * updateMainAddress(Long userId, Long addressId).
   * mainAddress로 지정하고 싶은 주소의 식별자 받아옴.
   * userAddressRepository.findById()로 해당 주소값 가져옴.
   * 해당 주소가 존재하는지, mainAddress로 지정할 권한이 있는지 확인.
   * 지금 mainAddress로 설정되어 있는 주소 해제.
   * 내가 원하는 주소를 mainAddress로 지정 및 저장.</p>
   */
  @Override
  @Transactional
  public UserAddressResult updateMainAddress(Long userId, Long addressId) {
    UserAddress userAddress = userAddressRepository.findById(addressId);
    checkUserAddressExistence(userAddress);
    checkUserAuthority(userId, userAddress);

    makeCurrentMainAddressFalse(userId);

    userAddress.setMainAddress(true);
    UserAddress result = userAddressRepository.save(userAddress);

    return UserAddressResult.fromUserAddress(result);
  }

  @Override
  @Transactional
  public UserAddressResult updateAddress(Long addressId, Long userId,
      SaveAddressCommand addressCommand) {

    UserAddress userAddress = userAddressRepository.findById(addressId);
    checkUserAddressExistence(userAddress);
    checkUserAuthority(userId, userAddress);

    makeCurrentMainAddressFalse(userId);

    userAddress.changeAddress(
        addressCommand.getLatitude(),
        addressCommand.getLongitude(),
        addressCommand.getAddressDetail(),
        true);

    UserAddress changedUserAddress = userAddressRepository.save(userAddress);
    return UserAddressResult.fromUserAddress(changedUserAddress);
  }

  @Override
  @Transactional
  public boolean deleteAddress(Long addressId, Long userId) {
    UserAddress userAddress = userAddressRepository.findById(addressId);
    checkUserAddressExistence(userAddress);
    checkUserAuthority(userId, userAddress);
    if (userAddress.getId() == userAddressRepository.findMainAddress(userId).getId()) {
      throw new CanNotDeleteMainAddressException("현재 주소와 삭제하려는 주소가 일치합니다.");
    }
    return userAddressRepository.deleteById(addressId);
  }

  /**
   * 주소 존재 여부 확인.
   */
  private void checkUserAddressExistence(UserAddress userAddress) {
    if (userAddress == null) {
      throw new UserAddressNotExistedException("존재하지 않는 주소입니다.");
    }
  }

  /**
   * 수정과 삭제 권한 처리.
   */
  private void checkUserAuthority(Long userId, UserAddress userAddress) {
    if (!Objects.equals(userId, userAddress.getUserId())) {
      throw new NotAuthorizedException("권한이 없습니다.");
    }
  }

  /**
   * 기존의 mainAddress를 false로 변경.
   */
  private void makeCurrentMainAddressFalse(Long userId) {
    UserAddress currentMainAddress = userAddressRepository.findMainAddress(userId);
    if (currentMainAddress != null) {
      currentMainAddress.setMainAddress(false);
      userAddressRepository.save(currentMainAddress);
    }
  }
}
