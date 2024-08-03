package com.fabiankevin.sample_user_api.services.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserCommand {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String email;
}
