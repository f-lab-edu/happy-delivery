package com.happy.delivery.application.ceo;

import com.happy.delivery.application.ceo.command.CeoSigninCommand;
import com.happy.delivery.application.ceo.result.CeoResult;
import com.happy.delivery.domain.ceo.Ceo;
import com.happy.delivery.domain.ceo.repository.CeoRepository;
import com.happy.delivery.domain.exception.ceo.EmailDuplicateException;
import com.happy.delivery.domain.exception.user.EmailIsNotMatchException;
import com.happy.delivery.domain.exception.user.PasswordIsNotMatchException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.encoder.EncryptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CeoServiceV1.
 */
@Service
public class CeoServiceV1 implements CeoService {

  private final CeoRepository ceoRepository;
  private final UserRepository userRepository;
  private final EncryptMapper encryptMapper;

  /**
   * CeoServiceV1 Constructor.
   */
  @Autowired
  public CeoServiceV1(CeoRepository ceoRepository,
      UserRepository userRepository, EncryptMapper encryptMapper) {
    this.ceoRepository = ceoRepository;
    this.userRepository = userRepository;
    this.encryptMapper = encryptMapper;
  }

  @Override
  public CeoResult ceoSignup(CeoSigninCommand ceoSigninCommand) {
    String useEmail = ceoSigninCommand.getEmail();
    Ceo ceoByEmail = ceoRepository.findByCeoEmail(useEmail);
    User userByEmail = userRepository.findByEmail(useEmail);

    if (ceoByEmail != null || userByEmail != null) {
      throw new EmailDuplicateException("이미 존재하는 계정 입니다.");
    }

    Ceo ceoResult = new Ceo(
        useEmail,
        encryptMapper.encoder(ceoSigninCommand.getPassword())
    );

    Ceo ceo = ceoRepository.ceoSave(ceoResult);

    return CeoResult.fromCeo(ceo);
  }

  @Override
  public CeoResult ceoSignin(CeoSigninCommand ceoSigninCommand) {
    Ceo ceoEmail = ceoRepository.findByCeoEmail(ceoSigninCommand.getEmail());
    if (ceoEmail == null) {
      throw new EmailIsNotMatchException("이메일이 일치하지 않습니다.");
    }

    if (!encryptMapper.isMatch(ceoSigninCommand.getPassword(), ceoEmail.getPassword())) {
      throw new PasswordIsNotMatchException("패스워드가 일치하지 않습니다."); //password
    }
    return CeoResult.fromCeo(ceoEmail);
  }
}
