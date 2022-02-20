package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    User findById(Long id);
    User changePassword(Long id, String encryptedPassword);
}
