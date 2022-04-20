package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserAddressResult;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.exception.user.CanNotDeleteMainAddressException;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NoUserException;
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

  /**
   * signup.
   * 회원가입.
   * email로 user 조회해서 null이 아니면 이미 존재하는 계정이니 오류 발생.
   * email 조회해서 null이면 비밀번호 암호화 해서 DB에 저장.
   *
   */
  @Override
  @Transactional
  public UserResult signup(SignupCommand signCommand) {
    if (userRepository.findByEmail(signCommand.getEmail()) != null) {
      throw new UserAlreadyExistedException("이미 존재하는 계정 입니다.");
    }
    User userResult = new User(
        signCommand.getEmail(),
        encryptMapper.encoder(signCommand.getPassword()),
        signCommand.getName(),
        signCommand.getPhoneNumber()
    );
    userRepository.save(userResult);
    return UserResult.fromUser(userResult);
  }

  /**
   * signin.
   * 로그인.
   * DB에 저장된 이메일과 비밀번호를 request로 받아온 값과 비교하기.
   * 만약 이메일이 다르다면 EmailIsNotMatchException 오류 발생.
   * 만약 비밀번호가 다르다면 PasswordIsNotMatchException 오류 발생.
   * 둘 다 맞다면 controller로 보내서 새션 생성.
   */
  @Override
  @Transactional
  public UserResult signin(SigninCommand signinCommand) {
    User user = userRepository.findByEmail(signinCommand.getEmail());
    if (user == null) {
      throw new EmailIsNotMatchException("이메일이 일치하지 않습니다.");
    }
    if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("패스워드가 일치하지 않습니다.");
    }
    return UserResult.fromUser(user);
  }

  /**
   * getMyAccount.
   * 개인 정보 가져오기.
   * 세션에 저장된 식별자를 이용해 개인 정보를 가져온다.
   */
  @Override
  @Transactional
  public UserResult getMyAccount(Long userId) {
    User user = userRepository.findById(userId);
    checkUserExistence(user);
    return UserResult.fromUser(user);
  }

  /**
   * updateMyAccount.
   * 개인 정보 수정.
   * 수정하는 정보들 : 이메일, 이름, 전화번호.
   * 경우 1. user1, user2 생성되어있는 상태 -> user1이 user2의 이메일로 변경 요청 ; (변경안됨)
   * 경우 2. user1, user2 생성되어있는 상태 -> user1이 이메일은 그대로, 이름과 폰 번호만 변경 요청 ; (변경됨)
   * 경우 3. user1, user2 생성되어있는 상태 -> user1이 user2의 이메일이 아닌, 중복 없는 이메일로 변경 요청 ; (변경됨)
   * 바꾸려는 이메일이 DB에 들어있는지 확인.
   * 이미 DB에 저장된 이메일인데 중복된 이메일이 타인의 것인 경우 UserAlreadyExistedException 오류 발생.
   * DB에 중복된 이메일이 없거나, 중복된 이메일이 내 것인 경우 save로 저장.
   */
  @Override
  @Transactional
  public UserResult updateMyAccount(MyAccountCommand myAccountCommand) {
    User byEmail = userRepository.findByEmail(myAccountCommand.getEmail());
    User user = userRepository.findById(myAccountCommand.getId());
    checkUserExistence(user);
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
    checkUserExistence(user);
    if (!encryptMapper.isMatch(passwordUpdateCommand.getCurrentPassword(), user.getPassword())) {
      throw new PasswordIsNotMatchException("현재 패스워드가 일치하지 않습니다.");
    }
    user.changePassword(encryptMapper.encoder(passwordUpdateCommand.getChangedPassword()));
    userRepository.save(user);
    return UserResult.fromUser(user);
  }

  @Override
  @Transactional
  public void deleteMyAccount(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  @Transactional
  public UserAddressResult saveAddress(Long userId, AddressCommand addressCommand) {
    makeCurrentMainAddressFalse(userId);
    UserAddress newAddress = userAddressRepository.save(
        new UserAddress(
            userId,
            addressCommand.getLongitude(),
            addressCommand.getLatitude(),
            addressCommand.getAddressDetail(),
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
      AddressCommand addressCommand) {

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
  public void deleteAddress(Long addressId, Long userId) {
    UserAddress userAddress = userAddressRepository.findById(addressId);
    checkUserAddressExistence(userAddress);
    checkUserAuthority(userId, userAddress);

    if (userAddress.getId() == userAddressRepository.findByUserIdAndMainAddressIsTrue(userId)
        .getId()) {
      throw new CanNotDeleteMainAddressException("현재 주소와 삭제하려는 주소가 일치합니다.");
    }
    userAddressRepository.deleteById(addressId);
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
   * repository에 해당 user가 있는지 확인.
   */
  private void checkUserExistence(User user) {
    if (user == null) {
      throw new NoUserException();
    }
  }

  /**
   * 기존의 mainAddress를 false로 변경.
   */
  private void makeCurrentMainAddressFalse(Long userId) {
    UserAddress currentMainAddress = userAddressRepository.findByUserIdAndMainAddressIsTrue(userId);
    if (currentMainAddress != null) {
      currentMainAddress.setMainAddress(false);
      userAddressRepository.save(currentMainAddress);
    }
  }
}
