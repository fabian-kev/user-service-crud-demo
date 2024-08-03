package com.fabiankevin.sample_user_api.services;

import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.persistence.UserRepository;
import com.fabiankevin.sample_user_api.services.command.CreateUserCommand;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultUserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new DefaultUserService(userRepository);

    @Test
    void createUser_givenValidCreateUserCommand_thenShouldMapAllFields() {
        CreateUserCommand command = new CreateUserCommand();
        command.setBirthDate(LocalDate.now());
        command.setFirstName("Emily");
        command.setLastName("Carter");
        command.setEmail("emily.carter@test.com");

        User user = toModel(command);
        when(userRepository.create(any()))
                .thenReturn(user);

        userService.createUser(command);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1))
                .create(userCaptor.capture());
        User userCaptorValue = userCaptor.getValue();

        assertEquals(command.getFirstName(), userCaptorValue.getFirstName(), "first name");
        assertEquals(command.getLastName(), userCaptorValue.getLastName(), "last name");
        assertEquals(command.getEmail(), userCaptorValue.getEmail(), "email");
        assertEquals(command.getBirthDate(), userCaptorValue.getBirthDate(), "birthday");
    }

    private static User toModel(CreateUserCommand command) {
        return User.builder()
                .email(command.getEmail())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .birthDate(command.getBirthDate())
                .updatedDate(Instant.now())
                .createdDate(Instant.now())
                .build();
    }
}
