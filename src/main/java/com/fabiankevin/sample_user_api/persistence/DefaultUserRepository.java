package com.fabiankevin.sample_user_api.persistence;

import com.fabiankevin.sample_user_api.mappers.UserMapper;
import com.fabiankevin.sample_user_api.models.User;
import com.fabiankevin.sample_user_api.persistence.entities.UserEntity;
import com.fabiankevin.sample_user_api.persistence.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DefaultUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> retrieveById(UUID id) {
        return jpaUserRepository.findById(id)
                .map(userMapper::toModel);
    }

    @Override
    public List<User> retrieveAll() {
        return jpaUserRepository.findAll().stream()
                .map(userMapper::toModel)
                .toList();
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return userMapper.toModel(savedUserEntity);
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);
        return userMapper.toModel(savedUserEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }
}
