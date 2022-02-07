package com.happy.delivery.infra.repository;

import com.happy.delivery.domain.User;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserHashMapRepository implements UserRepository{

    private final ConcurrentHashMap<Long, User> repo= new ConcurrentHashMap();
    private Long sequence = 1L;

    @Override
    public void save(User user) {
        sequence++;
        repo.put(sequence, user);
    }
}
