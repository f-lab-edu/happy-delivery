package com.happy.delivery.application.user;
import com.happy.delivery.application.user.command.SigninCommand;
import com.happy.delivery.application.user.command.SignupCommand;
import com.happy.delivery.domain.exception.user.AuthenticationFailedException;
import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.application.user.result.SignupResult;
import com.happy.delivery.domain.exception.user.EmailAlreadyUserException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceV1 implements UserService{

    private final UserRepository userRepository;
    private final EncryptMapper encryptMapper;
    private final HttpSession httpSession;
    public static final String EMAIL_ADDRESS = "EmailAddress";

    @Autowired
    public UserServiceV1(UserRepository userRepository, EncryptMapper encryptMapper, HttpSession session) {
        this.userRepository = userRepository;
        this.encryptMapper = encryptMapper;
        this.httpSession = session;
    }

    @Override
    public SignupResult signup(SignupCommand signCommand) {
        User user = new User(
                signCommand.getEmail(),
                this.getEncryptedPassword(signCommand), // 패스워드 암호화 로직
                signCommand.getName(),
                signCommand.getPhoneNumber()
        );
        userRepository.emailDuplicateCheck(user.getEmail());
        User result = userRepository.save(user);
        return new SignupResult().toSignupResult(result);
    }

    //패스워드 암호화
    public String getEncryptedPassword(SignupCommand signupCommand) {
        return encryptMapper.encoder(signupCommand.getPassword());
    }

//    석지애 코드 :: 매개변수를 String으로 받아옴
//    public String getEncryptedPassword(String password) {
//        return encryptMapper.encoder(password);
//    }

    @Override
    public String signin(SigninCommand signinCommand) {
        // 1. repo에 저장된 비밀번호 가져오기
        User user = signinCommand.toUser();
        String hashedPassword;
        try{
            hashedPassword = userRepository.getPassword(user.getEmail());
        } catch (NullPointerException ex){
            throw new AuthenticationFailedException();
        }

        // 2. 비밀번호 맞는지 확인
        boolean result = encryptMapper.isMatch(signinCommand.getPassword(), hashedPassword);
        // 3. 비밀번호가 일치하면
        if(result) {
            httpSession.setAttribute(EMAIL_ADDRESS, user.getEmail());
            return user.getEmail();
        }
        // 4. 비밀번호가 일치하지 않는다면, exception 발생시켜 사용자에게 보내준다.
        else {
            throw new AuthenticationFailedException();
        }
    }
}
