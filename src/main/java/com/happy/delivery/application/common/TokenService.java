package com.happy.delivery.application.common;

import com.happy.delivery.application.common.command.TokenCommand;

/**
 * TokenService.
 * 토큰 저장 로직을 위한 인터페이스.
 */
public interface TokenService {

  void saveToken(TokenCommand tokenCommand);

}
