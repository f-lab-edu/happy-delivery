package com.happy.delivery.application.user;

import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.domain.exception.user.AuthenticationFailedException;
import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.application.user.result.SignupResult;
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
    public SignupResult signup(SignupCommand signCommand) {
        User user = new User(
                signCommand.getEmail(),
                this.getEncryptedPassword(signCommand.getPassword()), // 패스워드 암호화 로직
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );
        userRepository.emailDuplicateCheck(user.getEmail());
        User result = userRepository.save(user);
        return SignupResult.fromUser(result); //수정필요
    }

    //패스워드 암호화
    //가독성이 필요할때 메서드 안에 내용이 길때
    private String getEncryptedPassword(String plainText) {//string text
        return encryptMapper.encoder(plainText);
    }

//    석지애 코드 :: 매개변수를 String 으로 받아옴
//    public String getEncryptedPassword(String password) {
//        return encryptMapper.encoder(password);
//    }

    @Override
    public String signin(SigninCommand signinCommand) {
        // 1. repo에 저장된 비밀번호 가져오기
        User user = userRepository.findByEmail(signinCommand.getEmail());
        if (user == null) throw new AuthenticationFailedException(); //email

        // 2. 비밀번호 맞는지 확인
        if (!encryptMapper.isMatch(signinCommand.getPassword(), user.getPassword())) {
            throw new AuthenticationFailedException(); //password
        }
        return user.getEmail();
    }
}
