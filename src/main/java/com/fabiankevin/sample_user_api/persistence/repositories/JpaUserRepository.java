package com.fabiankevin.sample_user_api.persistence.repositories;

import com.fabiankevin.sample_user_api.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
}
