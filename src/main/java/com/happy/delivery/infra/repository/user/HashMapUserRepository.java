package com.happy.delivery.infra.repository.user;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HashMapUserRepository implements UserRepository {
    private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    @Override
    public User save(User user) {
        sequence++;
        hashmap.put(sequence, user);
        return user;
    }

    @Override
    public boolean emailDuplicateCheck(String email) {//중복값 체크
        boolean findEmailResult = hashmap
                .values()
                .stream()
                .filter(findEmail -> email.equals(findEmail.getEmail()))
                .findFirst()
                .isPresent();//값이 없으면 false를 반환하고 있으면 true 반환한다

        return findEmailResult;
    }

    @Override
    public String getPassword(String email) {
         Optional<User> result = hashmap
                .values()
                .stream()
                .filter(findEmail -> email.equals(findEmail.getEmail()))
                .findFirst();
         //만약 return값이 없다면 어떻게 처리할것인가?

       User user = result.get();

        return user.getPassword();
    }
}
