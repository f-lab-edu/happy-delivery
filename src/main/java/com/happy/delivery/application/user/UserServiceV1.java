package com.happy.delivery.application.user;
import com.happy.delivery.application.command.SignupCommand;
import com.happy.delivery.application.result.SignupResult;
import com.happy.delivery.domain.exception.EmailAlreadyUserException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
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
    public SignupResult signup(SignupCommand signCommand){
        //비밀번호 암호화
        //비밀번호 복호화

        User user = new User(
                signCommand.getEmail(),
                signCommand.getPassword(),
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
