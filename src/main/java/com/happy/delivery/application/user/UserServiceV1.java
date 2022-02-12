package com.happy.delivery.application.user;
import com.happy.delivery.application.encrypt.WebSecurityConfig;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.application.encrypt.Sha256Encrypt;
import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.domain.exception.EmailAlreadyUserException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceV1 implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceV1(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SignupResult signup(SignupCommand signCommand) throws NoSuchAlgorithmException {
        //비밀번호 암호화
        //비밀번호 복호화 - 복호화는 안해도됨. 로그인할때 로그인 받은 암호를 암호화해서 암호화한 값끼리 비교 하면될듯 (확인함)
        Sha256Encrypt sha256Encrypt = new Sha256Encrypt();
        String PasswordEncode = passwordEncoder.encode((signCommand.getPassword()));
        //String passwordEncrypt = sha256Encrypt.encrypt(signCommand.getEmail());
        User user = new User(
                signCommand.getEmail(),
                PasswordEncode,
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );

        boolean emailDuplicateCheck = userRepository.emailDuplicateCheck(user.getEmail());
        if(emailDuplicateCheck) throw new EmailAlreadyUserException();
        User result = userRepository.save(user);
        SignupResult signupResult = result.toSignupResult(result);
        return signupResult;
    }
}
