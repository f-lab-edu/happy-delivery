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
        User check = emailDuplicateCheck(user);
        if(check==null) hashmap.put(sequence, user);
        return user;
    }

    @Override
    public User emailDuplicateCheck(User user) {//중복값 체크
        String email = user.getEmail();
        User result = hashmap
                .values()
                .stream()
                .filter(userFindEmail -> email.equals(userFindEmail.getEmail()))
                .findFirst()
                .orElse(null);//같은 이메일이 없으면 null 반환, 있으면 객체 반환
        return result;
    }
}
