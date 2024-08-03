package com.fabiankevin.sample_user_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private UUID id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String email;
    private Instant createdDate;
    private Instant updatedDate;
}
