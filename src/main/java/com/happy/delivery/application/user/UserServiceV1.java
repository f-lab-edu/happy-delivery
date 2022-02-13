package com.happy.delivery.application.user;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.domain.exception.EmailAlreadyUserException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceV1 implements UserService{

    private final UserRepository userRepository;
    private final EncryptMapper encryptMapper;

    @Autowired
    public UserServiceV1(UserRepository userRepository, EncryptMapper encryptMapper) {
        this.userRepository = userRepository;
        this.encryptMapper = encryptMapper;
    }


    @Override
    public SignupResult signup(SignupCommand signCommand) throws NoSuchAlgorithmException {
        //비밀번호 암호화
        //비밀번호 복호화 - 복호화는 안해도됨. 로그인할때 로그인 받은 암호를 암호화해서 암호화한 값끼리 비교 하면될듯 (확인함)
        String passwordEncrypt = encryptMapper.encoder(signCommand.getPassword());

        User user = new User(
                signCommand.getEmail(),
                passwordEncrypt,
                signCommand.getName(),
                signCommand.getPhoneNumber(),
                null
        );

        boolean emailDuplicateCheck = userRepository.emailDuplicateCheck(user.getEmail());
        if(emailDuplicateCheck) throw new EmailAlreadyUserException();
        User result = userRepository.save(user);
        SignupResult signupResult = result.toSignupResult();
        return signupResult;
    }

    @Override
    public void signin(SigninCommand signinCommand) {
        // 1. repo에 저장된 비밀번호 가져오기
        User user = signinCommand.toUser();
        String hashedPassword = userRepository.getPassword(user.getEmail());
        // 2. 비밀번호 맞는지 확인
        boolean result = encryptMapper.isMatch(signinCommand.getPassword(), hashedPassword);
        //비밀번호가 일치하면
        if(result) {
            //세션 설정
        }

        //비밀번호가 일치하지 않는다면,

    }
}
