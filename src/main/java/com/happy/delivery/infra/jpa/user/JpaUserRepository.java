package com.happy.delivery.infra.jpa.user;

import com.happy.delivery.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaUserRepository.
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {

  User save(User user);

  Optional<User> findById(Long id);

  User findByEmail(String email);

  void deleteById(Long id);
}
