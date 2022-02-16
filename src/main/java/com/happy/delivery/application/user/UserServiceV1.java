package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.exception.user.UserAlreadyExistedException;
import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.application.user.result.UserResult;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
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
            throw new UserAlreadyExistedException();
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
        if (user == null) throw new EmailIsNotMatchException(); //EmailIsNotMatch
        // 2. 비밀번호 맞는지 확인
        if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
            throw new PasswordIsNotMatchException(); //password
        }
        return UserResult.fromUser(user);
    }
}
