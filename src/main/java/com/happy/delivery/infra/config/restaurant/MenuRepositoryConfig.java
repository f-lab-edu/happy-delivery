package com.happy.delivery.infra.config.restaurant;

import com.happy.delivery.domain.restaurant.repository.MenuRepository;
import com.happy.delivery.infra.jpa.restaurant.JpaMenuRepository;
import com.happy.delivery.infra.repository.user.adapter.JpaMenuRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MenuRepositoryConfig.
 * JPA 를 사용할 것인지 MyBatis 를 사용할 것인지 결정.
 */
@Configuration
public class MenuRepositoryConfig {

  @Autowired
  private JpaMenuRepository jpaMenuRepository;

  @Bean
  public MenuRepository jpaMenuRepositoryAdapter() {
    return new JpaMenuRepositoryAdapter(jpaMenuRepository);
  }
}
