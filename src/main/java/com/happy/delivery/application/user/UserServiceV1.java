package com.happy.delivery.application.user;

import com.happy.delivery.application.command.SignCommand;
import com.happy.delivery.application.response.SignResponse;
import com.happy.delivery.domain.User;
import com.happy.delivery.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceV1(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignResponse signup(SignCommand signCommand) {

        //비밀번호 암호화

        //UserEntity 만들기
        //** 질문 **
        User user = new User(
                signCommand.getEmail(),
                signCommand.getPassword(),
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );

        //이메일 중복 검사

        //repository에 저장
        userRepository.save(user);
        SignResponse signResponse = signCommand.fromSignResponse(signCommand);
        return signResponse;
    }

}
