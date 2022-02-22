package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

public interface UserRepository {
    public User save(User user);

    public User findById(Long id);

    public User findByEmail(String email);

    public User changePassword(Long id, String encryptedPassword);

    public User saveAddress(Long id, String address);
}
