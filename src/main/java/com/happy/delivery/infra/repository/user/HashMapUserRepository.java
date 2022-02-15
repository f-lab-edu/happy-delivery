package com.happy.delivery.infra.repository.user;

import com.happy.delivery.domain.exception.user.EmailAlreadyUserException;
import com.happy.delivery.domain.user.User;
import com.happy.delivery.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HashMapUserRepository implements UserRepository {
    private final Map<Long, User> hashmap = new ConcurrentHashMap<>();
    private Long sequence = 0L; // -> AtomicLong increate 값 증가, id

    @Override
    public User save(User user) {
        sequence++;
        hashmap.put(sequence, user);
        return user;
    }

    @Override
    public void emailDuplicateCheck(String email) {//중복값 체크
        boolean present = hashmap
                .values()
                .stream()
                .filter(findEmail -> email.equals(findEmail.getEmail()))
                .findFirst()
                .isPresent();        //값이 없으면 false를 반환하고 있으면 true 반환한다
        if(present){
            throw new EmailAlreadyUserException();
        }
    }

    //email을 받아서 저장된 repository에서 찾고 패스워드를 반환
    @Override
    public User findByEmail(String email) {
        return  hashmap
                .values()
                .stream()
                .filter(findEmail -> email.equals(findEmail.getEmail()))
                .findFirst()
                .orElse(null);// 값이 있으면 값을 반환하고 없으면 null반환
    }
}
