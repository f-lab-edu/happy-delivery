package com.happy.delivery.infra.repository.user.jpa;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JpaUserRepository.
 */
@Repository
@Transactional
public class JpaUserRepository implements UserRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public User save(User user) {
    em.persist(user);
    return user;
  }

  @Override
  public User findById(Long id) {
    return em.find(User.class, id);
  }

  @Override
  public User findByEmail(String email) {
    List<User> users = em.createQuery("select u from User u where u.email = :email", User.class)
        .setParameter("email", email)
        .getResultList();
    if (users.size() == 0) {
      return null;
    }
    return users.get(0);
  }

  @Override
  public boolean deleteId(Long id) {
    User user = em.find(User.class, id);
    em.remove(user);
    return true;
  }
}
