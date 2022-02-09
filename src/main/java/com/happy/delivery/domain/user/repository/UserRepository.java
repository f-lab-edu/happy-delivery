package com.happy.delivery.domain.user.repository;

import com.happy.delivery.domain.user.User;

public interface UserRepository {
    User save(User user);

    User emailDuplicateCheck(User user);
}
