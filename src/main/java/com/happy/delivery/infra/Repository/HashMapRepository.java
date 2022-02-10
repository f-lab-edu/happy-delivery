package com.happy.delivery.infra.Repository;

import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HashMapRepository implements UserRepository {
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
}
