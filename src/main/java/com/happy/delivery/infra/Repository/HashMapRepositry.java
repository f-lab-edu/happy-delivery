package com.happy.delivery.infra.Repository;

import com.happy.delivery.domain.User;
import com.happy.delivery.domain.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HashMapRepositry implements UserRepository {
    private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    @Override
    public void save(User user) {
        sequence++;//autometic?으로 한거 같은데..
        hashmap.put(sequence, user);
    }
}
