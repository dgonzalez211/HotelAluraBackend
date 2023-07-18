package org.diegodev.hotelalurabackend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private boolean admin;
}
