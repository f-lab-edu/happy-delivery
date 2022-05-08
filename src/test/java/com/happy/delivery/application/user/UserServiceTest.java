package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.MyAccountCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.enumeration.Authority;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.NoUserException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.encoder.EncryptMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * UserService Method Test.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  //매직넘버
  private static final Long ID = 1L;
  private static final String EMAIL = "test@email.com";
  private static final String UPDATED_EMAIL = "test1111@email.com";
  private static final String PASSWORD = "abcd1234";
  private static final String ERRORED_PASSWORD = "a4321";
  private static final String ENCRYPTED_PASSWORD = "asdf4321";
  private static final String NAME = "김프랩";
  private static final String UPDATED_NAME = "에프랩";
  private static final String PHONE_NUMBER = "12345678910";
  private static final String UPDATED_PHONE_NUMBER = "11122223333";
  private static final Authority USER_AUTHORITY = Authority.USER;
  //기본객체
  private UserRepository userRepository;
  private UserAddressRepository userAddressRepository;
  private EncryptMapper encryptMapper;
  private UserService userService;

  /**
   * settings.
   * 테스트 전에 미리 세팅.
   * userRepository, userAddressRepository, encryptMapper 의 Mock 생성.
   * UserService 객체 생성.
   */
  @BeforeEach
  public void settings() {
    this.userRepository = Mockito.mock(UserRepository.class);
    this.userAddressRepository = Mockito.mock(UserAddressRepository.class);
    this.encryptMapper = Mockito.mock(EncryptMapper.class);
    this.userService = new UserServiceV1(userRepository, userAddressRepository, encryptMapper);
  }

  @Test
  @DisplayName("이메일 중복이 없다면 회원가입은 성공해야 한다.")
  public void signup_success_case1__different_email() {
    //given
    SignupCommand command = new SignupCommand(EMAIL, PASSWORD, NAME, PHONE_NUMBER, USER_AUTHORITY);
    Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);
    Mockito.when(encryptMapper.encoder(PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    //when
    UserResult result = this.userService.signup(command);

    Mockito.verify(userRepository).save(userCaptor.capture());
    User savedUser = userCaptor.getValue();

    //then
    encryptedUserDataIsEqualTo(savedUser);
    userResultDataIsEqualTo(result);
  }

  @Test
  @DisplayName("이메일 중복이 있으면 회원가입 실패해야한다. & 예외 발생")
  public void signup_failure_case1__duplicated_email() {
    //given
    SignupCommand command = new SignupCommand(EMAIL, PASSWORD, NAME, PHONE_NUMBER, USER_AUTHORITY);
    Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(getUserFromRepository());

    //when
    //then
    Assertions.assertThatThrownBy(() -> {
      this.userService.signup(command);
    }).isInstanceOf(UserAlreadyExistedException.class);
  }

  @Test
  @DisplayName("이메일와 비밀번호가 DB에 저장되어있고, 두 값이 모두 일치하면 로그인한다.")
  public void signin_success_case1__existed_user_and_correct_password() {
    //given
    SigninCommand command = new SigninCommand(EMAIL, PASSWORD);
    Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(getUserFromRepository());
    Mockito.when(encryptMapper.isMatch(command.getPassword(), ENCRYPTED_PASSWORD))
        .thenReturn(true);

    //when
    UserResult result = this.userService.signin(command);

    //then
    userResultDataIsEqualTo(result);
  }

  @Test
  @DisplayName("계정 이메일이 DB에 없어서 로그인에 실패해야한다.")
  public void signin_failure_case1__user_email_does_not_exist() {
    //given
    SigninCommand command = new SigninCommand(EMAIL, PASSWORD);
    Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);

    //when
    //then
    Assertions.assertThatThrownBy(() -> {
      this.userService.signin(command);
    }).isInstanceOf(EmailIsNotMatchException.class);
  }

  @Test
  @DisplayName("계정은 있으나, 비밀번호가 틀려서 로그인에 실패해야한다.")
  public void signin_failure_case2__incorrect_password() {
    //given
    SigninCommand command = new SigninCommand(EMAIL, ERRORED_PASSWORD);
    Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(getUserFromRepository());
    Mockito.when(encryptMapper.isMatch(command.getPassword(), ENCRYPTED_PASSWORD))
        .thenReturn(false);

    //when
    //then
    Assertions.assertThatThrownBy(() -> {
      this.userService.signin(command);
    }).isInstanceOf(PasswordIsNotMatchException.class);
  }

  @Test
  @DisplayName("DB에 일치하는 ID가 있어서 사용자의 개인정보를 가져오는 것에 성공해야한다.")
  public void get_my_account_success_case1__existed_user() {
    //given
    Mockito.when(userRepository.findById(ID)).thenReturn(getUserFromRepository());

    //when
    UserResult userResult = this.userService.getMyAccount(ID);

    //then
    userResultDataIsEqualTo(userResult);
  }

  @Test
  @DisplayName("DB에 해당 ID가 없어서 사용자의 개인정보를 가져오는 것에 실패해야한다.")
  public void get_my_account_failure_case1__user_Id_does_not_exist() {
    //given
    Mockito.when(userRepository.findById(ID)).thenReturn(null);

    //when
    //then
    Assertions.assertThatThrownBy(() -> {
      this.userService.getMyAccount(ID);
    }).isInstanceOf(NoUserException.class);
  }

  @Test
  @DisplayName("수정하려는 계정이 DB에 존재하고, 중복된 이메일이 없으면 개인정보 업데이트에 성공해야 한다.")
  public void update_my_account_success_case2__duplicated_email_doesnt_exist() {
    //given
    MyAccountCommand command =
        new MyAccountCommand(ID, UPDATED_EMAIL, UPDATED_NAME, UPDATED_PHONE_NUMBER);
    Mockito.when(userRepository.findByEmail(UPDATED_EMAIL)).thenReturn(null);
    Mockito.when(userRepository.findById(ID)).thenReturn(getUserFromRepository());
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    //when
    UserResult userResult = this.userService.updateMyAccount(command);

    Mockito.verify(userRepository).save(userCaptor.capture());
    User updatedUser = userCaptor.getValue();

    //then
    updatedUserDataIsEqualTo(updatedUser);
    updatedUserResultDataIsEqualTo(userResult);
  }

  //assertions 메서드1 :: 암호화된 사용자 계정값 맞는지 확인
  private void encryptedUserDataIsEqualTo(User encryptedUser) {
    Assertions.assertThat(encryptedUser).isNotNull();
    Assertions.assertThat(encryptedUser.getEmail()).isEqualTo(EMAIL);
    Assertions.assertThat(encryptedUser.getName()).isEqualTo(NAME);
    Assertions.assertThat(encryptedUser.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    Assertions.assertThat(encryptedUser.getPassword()).isEqualTo(ENCRYPTED_PASSWORD);
  }

  //assertions 메서드2 :: 수정하는 사용자 정보값 맞는지 확인
  private void updatedUserDataIsEqualTo(User updatedUser) {
    Assertions.assertThat(updatedUser).isNotNull();
    Assertions.assertThat(updatedUser.getEmail()).isEqualTo(UPDATED_EMAIL);
    Assertions.assertThat(updatedUser.getName()).isEqualTo(UPDATED_NAME);
    Assertions.assertThat(updatedUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    Assertions.assertThat(updatedUser.getPassword()).isEqualTo(ENCRYPTED_PASSWORD);
  }

  //assertions 메서드3 :: 사용자 계정 결과값 맞는지 확인
  private void userResultDataIsEqualTo(UserResult userResult) {
    Assertions.assertThat(userResult).isNotNull();
    Assertions.assertThat(userResult.getEmail()).isEqualTo(EMAIL);
    Assertions.assertThat(userResult.getName()).isEqualTo(NAME);
    Assertions.assertThat(userResult.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
  }

  //assertions 메서드4 :: 수정된 사용자 계정 결과값 맞는지 확인
  private void updatedUserResultDataIsEqualTo(UserResult userResult) {
    Assertions.assertThat(userResult).isNotNull();
    Assertions.assertThat(userResult.getEmail()).isEqualTo(UPDATED_EMAIL);
    Assertions.assertThat(userResult.getName()).isEqualTo(UPDATED_NAME);
    Assertions.assertThat(userResult.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
  }

  //비밀번호가 암호화된 user 객체 만드는 메서드
  private User getUserFromRepository() {
    return new User(EMAIL, ENCRYPTED_PASSWORD, NAME, PHONE_NUMBER, USER_AUTHORITY);
  }
}
