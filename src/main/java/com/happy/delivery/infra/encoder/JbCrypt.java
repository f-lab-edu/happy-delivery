package com.happy.delivery.infra.encoder;

import org.mindrot.jbcrypt.BCrypt;


public class JbCrypt implements EncryptMapper {

    //비밀번호 암호화
    @Override
    public String encoder(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //비밀번호 일치 비교
    @Override
    public boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password,hashed);
    }
}
