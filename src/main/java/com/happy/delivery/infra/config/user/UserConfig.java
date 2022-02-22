package com.happy.delivery.infra.config.user;

import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.infra.encoder.JbCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Bean
  public EncryptMapper encryptMapper() {
    return new JbCrypt();
  }
}
