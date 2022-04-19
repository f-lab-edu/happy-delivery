package com.happy.delivery.infra.repository.user.adapter;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import com.happy.delivery.infra.jpa.user.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * JpaUserRepositoryAdapter.
 */
@Repository
public class JpaUserRepositoryAdapter implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Autowired
  public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }

  /**
   * save.
   * 주소 저장 및 수정.
   */
  public User save(User user) {
    return jpaUserRepository.save(user);
  }

  /**
   * findById.
   * 식별자로 값 찾기.
   */
  public User findById(Long id) {
    return jpaUserRepository.findById(id).orElse(null);
  }

  /**
   * findByEmail.
   * 이메일로 값 찾기.
   */
  public User findByEmail(String email) {
    return jpaUserRepository.findByEmail(email);
  }

  /**
   * deleteById.
   * 식별자로 삭제하기.
   */
  public void deleteById(Long id) {
    jpaUserRepository.deleteById(id);
  }
}
