package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.UserDTO;
import org.diegodev.hotelalurabackend.models.entities.User;

@NoArgsConstructor
public class UserDTOMapper {

    private User user;

    public static UserDTOMapper builder() {
        return new UserDTOMapper();
    }

    public UserDTOMapper setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDTO build() {
        if (user == null) {
            throw new RuntimeException("User entity in UserDTO is null!");
        }
        boolean isAdmin = user.getRoles().stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getName()));
        return new UserDTO(this.user.getId(), user.getUsername(), user.getEmail(), isAdmin);
    }


}
