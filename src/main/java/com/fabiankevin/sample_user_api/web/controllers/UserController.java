package com.fabiankevin.sample_user_api.web.controllers;

import com.fabiankevin.sample_user_api.mappers.UserMapper;
import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.services.UserService;
import com.fabiankevin.sample_user_api.services.command.CreateUserCommand;
import com.fabiankevin.sample_user_api.services.command.UpdateUserCommand;
import com.fabiankevin.sample_user_api.web.dto.CreateUserRequest;
import com.fabiankevin.sample_user_api.web.dto.UpdateUserRequest;
import com.fabiankevin.sample_user_api.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserResponse retrieveUserById(@PathVariable("id") UUID id) {
        return userMapper.toResponse(userService.retrieveById(id));
    }

    @GetMapping
    public List<UserResponse> retrieveAll() {
        return userService.retrieveAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest createUserRequest) {
        log.info("createUserRequest: {}", createUserRequest);
        User user = userService.createUser(toCommand(createUserRequest));

        return userMapper.toResponse(user);
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable("id") UUID id) {
        UpdateUserCommand updateUserCommand = toCommand(updateUserRequest);

        return userMapper.toResponse(userService.updateUser(updateUserCommand, id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
    }

    private static CreateUserCommand toCommand(CreateUserRequest createUserRequest) {
        return CreateUserCommand.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .birthDate(createUserRequest.getBirthDate())
                .build();
    }

    private static UpdateUserCommand toCommand(UpdateUserRequest updateUserRequest) {
        return UpdateUserCommand.builder()
                .firstName(updateUserRequest.getFirstName())
                .lastName(updateUserRequest.getLastName())
                .email(updateUserRequest.getEmail())
                .birthDate(updateUserRequest.getBirthDate())
                .build();
    }

}
