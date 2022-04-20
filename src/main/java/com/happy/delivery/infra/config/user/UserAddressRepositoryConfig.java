package com.happy.delivery.infra.config.user;

import com.happy.delivery.domain.user.repository.UserAddressRepository;
import com.happy.delivery.infra.jpa.user.JpaUserAddressRepository;
import com.happy.delivery.infra.repository.user.adapter.JpaUserAddressRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserRepositoryConfig.
 */
@Configuration
public class UserAddressRepositoryConfig {


  private final JpaUserAddressRepository jpaUserAddressRepository;

  @Autowired
  public UserAddressRepositoryConfig(
      JpaUserAddressRepository jpaUserAddressRepository) {
    this.jpaUserAddressRepository = jpaUserAddressRepository;
  }

  @Bean
  public UserAddressRepository jpaUserAddressRepositoryAdapter() {
    return new JpaUserAddressRepositoryAdapter(jpaUserAddressRepository);
  }

}
