package com.fabiankevin.sample_user_api.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id=%s hasn't found.", id));
    }
}
