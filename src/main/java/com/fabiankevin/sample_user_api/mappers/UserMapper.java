package com.fabiankevin.sample_user_api.mappers;

import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.persistence.entities.UserEntity;
import com.fabiankevin.sample_user_api.web.dto.UserResponse;

public interface UserMapper {
    User toModel(UserEntity entity);

    UserEntity toEntity(User user);

    UserResponse toResponse(User user);
}
