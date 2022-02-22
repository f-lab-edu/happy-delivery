package com.happy.delivery.infra.repository.user;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HashMapUserRepository implements UserRepository {

  private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

  @Override
  public User save(User user) {
    long UserId = id.incrementAndGet();
    User UserHasId = new User(
        UserId,
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getPhoneNumber()
    );
    hashmap.put(UserId, UserHasId);
    return UserHasId;
  }

  //email을 받아서 저장된 repository에서 찾고 패스워드를 반환
  @Override
  public User findByEmail(String email) {
    return hashmap
        .values()
        .stream()
        .filter(findEmail -> email.equals(findEmail.getEmail()))
        .findFirst()
        .orElse(null);// 값이 있으면 값을 반환하고 없으면 null반환
  }

  @Override
  public User findById(Long id) {
    return hashmap.get(id);
  }

  @Override
  public User changePassword(Long id, String encryptedPassword) {
    User currentUser = this.findById(id);
    User result = new User(
        currentUser.getId(),
        currentUser.getEmail(),
        encryptedPassword,
        currentUser.getName(),
        currentUser.getPhoneNumber()
    );
    hashmap.replace(id, result);
    return result;
  }

  @Override
  public User saveAddress(Long id, String address) {
    User user = this.findById(id);
    User result = new User(
        user.getId(),
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getPhoneNumber(),
        address
    );
    hashmap.replace(id, result);
    return result;
  }
}
