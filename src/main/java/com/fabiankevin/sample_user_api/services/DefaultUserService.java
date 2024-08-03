package com.fabiankevin.sample_user_api.services;

import com.fabiankevin.sample_user_api.exceptions.UserNotFoundException;
import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.persistence.UserRepository;
import com.fabiankevin.sample_user_api.services.command.CreateUserCommand;
import com.fabiankevin.sample_user_api.services.command.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User updateUser(UpdateUserCommand command, UUID id) {
        User updatedUser = userRepository.retrieveById(id)
                .map(user -> {
                    user.setFirstName(command.getFirstName());
                    user.setLastName(command.getLastName());
                    user.setBirthDate(command.getBirthDate());
                    user.setEmail(command.getEmail());

                    user.setUpdatedDate(Instant.now());
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException(id));

        return userRepository.update(updatedUser);
    }

    @Override
    public User createUser(CreateUserCommand command) {
        User user = toUser(command);
        user.setCreatedDate(Instant.now());
        user.setUpdatedDate(Instant.now());
        return userRepository.create(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User retrieveById(UUID id) {
        return userRepository.retrieveById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> retrieveAll() {
        return userRepository.retrieveAll();
    }

    private static User toUser(CreateUserCommand command) {
        return User.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .birthDate(command.getBirthDate())
                .email(command.getEmail())
                .build();
    }
}
