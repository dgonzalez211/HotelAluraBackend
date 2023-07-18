package org.diegodev.hotelalurabackend.models.request;

import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UserRequest implements IUser {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 8)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String phoneNumber;

    private boolean admin;
    
}
