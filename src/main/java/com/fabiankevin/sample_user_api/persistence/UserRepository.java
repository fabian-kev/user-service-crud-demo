package com.fabiankevin.sample_user_api.persistence;

import com.fabiankevin.sample_user_api.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> retrieveById(UUID id);

    List<User> retrieveAll();

    User update(User user);

    User create(User user);

    void deleteById(UUID id);
}
