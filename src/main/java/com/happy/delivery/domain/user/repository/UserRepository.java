package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

public interface UserRepository {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    User changePassword(Long id, String encryptedPassword);
}
