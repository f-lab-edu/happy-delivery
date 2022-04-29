package com.happy.delivery.infra.config.user;

import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.jpa.user.JpaUserRepository;
import com.happy.delivery.infra.repository.user.adapter.JpaUserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserRepositoryConfig.
 */
@Configuration
public class UserRepositoryConfig {

  @Autowired
  private JpaUserRepository jpaUserRepository;

  @Bean
  public UserRepository jpaUserRepositoryAdapter() {
    return new JpaUserRepositoryAdapter(jpaUserRepository);
  }

}
