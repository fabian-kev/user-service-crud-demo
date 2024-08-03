package com.fabiankevin.sample_user_api.services;

import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.services.command.CreateUserCommand;
import com.fabiankevin.sample_user_api.services.command.UpdateUserCommand;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User updateUser(UpdateUserCommand command, UUID id);

    User createUser(CreateUserCommand command);

    void deleteById(UUID id);

    User retrieveById(UUID id);

    List<User> retrieveAll();
}
