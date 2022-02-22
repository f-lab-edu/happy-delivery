package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.AddressCommand;
import com.happy.delivery.application.user.command.PasswordUpdateCommand;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.encoder.EncryptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 implements UserService {

    private final UserRepository userRepository;
    private final EncryptMapper encryptMapper;

    @Autowired
    public UserServiceV1(UserRepository userRepository, EncryptMapper encryptMapper) {
        this.userRepository = userRepository;
        this.encryptMapper = encryptMapper;
    }

    @Override
    public UserResult signup(SignupCommand signCommand) {
        // email로 user 조회
        // null이 아니면, 이미 존재하는 계정이니 예외 발생
        if(userRepository.findByEmail(signCommand.getEmail()) != null){
            throw new UserAlreadyExistedException("이미 존재하는 계정 입니다.");
        }
        // password를 hash 암호화하여 repository에 저장
        User result = userRepository.save(
            new User(
                signCommand.getEmail(),
                encryptMapper.encoder(signCommand.getPassword()), // 패스워드 암호화 로직
                signCommand.getName(),
                signCommand.getPhoneNumber()
            ));
        // 저장했다면 dto를 리턴하여 종료
        return UserResult.fromUser(result);
    }

    @Override
    public UserResult signin(SigninCommand signinCommand) {
        // 1. repo에 저장된 비밀번호 가져오기
        User user = userRepository.findByEmail(signinCommand.getEmail());
        if (user == null) throw new EmailIsNotMatchException("이메일이 일치하지 않습니다."); //EmailIsNotMatch
        // 2. 비밀번호 맞는지 확인
        if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
            throw new PasswordIsNotMatchException("패스워드가 일치하지 않습니다."); //password
        }
        return UserResult.fromUser(user);
    }

    @Override
    public UserResult updatePassword(Long id, PasswordUpdateCommand passwordUpdateCommand) {
        //변경 전 비밀번호 일치여부 검사
        User user = userRepository.findById(id);
        if(!encryptMapper.isMatch(passwordUpdateCommand.getCurrentPassword(), user.getPassword())) {
            throw new PasswordIsNotMatchException("현재 패스워드가 일치하지 않습니다.");
        }
        //바꾸려는 비밀번호 암호화 + repo에 저장
        User result = userRepository.changePassword(
            id,
            encryptMapper.encoder(passwordUpdateCommand.getChangedPassword()
            ));
        return UserResult.fromUser(user);
    }

    @Override
    public UserResult saveAddress(Long id, AddressCommand address) {
        User user = userRepository.saveAddress(id, address.makeTotalAddress());
        return UserResult.fromAddressUser(user);
    }
}
