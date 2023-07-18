package org.diegodev.hotelalurabackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private boolean admin;
}
