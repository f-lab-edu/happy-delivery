package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

public interface UserRepository {
    User save(User user);
    void emailDuplicateCheck(String email);
    User findByEmail(String email);
}
