package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User save(User user);

  Optional<User> findById(Long id);

  User findByEmail(String email);

  void deleteById(Long id);

}
