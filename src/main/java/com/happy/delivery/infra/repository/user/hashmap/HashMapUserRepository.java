package com.happy.delivery.infra.repository.user.hashmap;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HashMapUserRepository.
 * repository는 collection의 역할을 하기때문에 비지니스 로직이 들어가면 안된다.
 */
public class HashMapUserRepository implements UserRepository {

  private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

  /**
   * save.
   * 주소 저장 및 수정.
   */
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

  /**
   * findById.
   * 식별자로 값 찾기.
   */
  public User findById(Long id) {
    return hashmap.get(id);

  }

  /**
   * findByEmail.
   * 이메일로 값 찾기.
   */
  public User findByEmail(String email) {
    return hashmap
        .values()
        .stream()
        .filter(findEmail -> email.equals(findEmail.getEmail()))
        .findFirst()
        .orElse(null);
  }

  /**
   * deleteById.
   * 식별자로 삭제하기.
   */
  public void deleteById(Long id) {
    hashmap.remove(id);
  }
}
