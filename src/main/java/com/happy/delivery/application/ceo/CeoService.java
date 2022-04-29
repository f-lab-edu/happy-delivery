package com.happy.delivery.application.ceo;


import com.happy.delivery.application.ceo.command.CeoSigninCommand;
import com.happy.delivery.application.ceo.result.CeoResult;

/**
 * CeoService.
 */
public interface CeoService {

  CeoResult ceoSignup(CeoSigninCommand ceoSigninCommand);

  CeoResult ceoSignin(CeoSigninCommand ceoSigninCommand);

}
