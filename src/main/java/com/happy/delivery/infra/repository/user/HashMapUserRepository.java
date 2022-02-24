package com.happy.delivery.infra.repository.user;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/**
 * HashMapUserRepository.
 * repository는 collection의 역할을 하기때문에 비지니스 로직이 들어가면 안된다.
 */
@Repository
public class HashMapUserRepository implements UserRepository {

  private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

  @Override
  public User save(User user) {
    if (user.getId() == null || user.getId() <= 0L) {
      Long userId = id.incrementAndGet();
      user.setId(userId);
      hashmap.put(userId, user);
    } else {
      hashmap.put(user.getId(), user);
    }
    return user;
  }

  //email을 받아서 저장된 repository에서 찾고 패스워드를 반환
  @Override
  public User findByEmail(String email) {
    return hashmap
        .values()
        .stream()
        .filter(findEmail -> email.equals(findEmail.getEmail()))
        .findFirst()
        .orElse(null);
  }

  @Override
  public User findById(Long id) {
    return hashmap.get(id);
  }
}
