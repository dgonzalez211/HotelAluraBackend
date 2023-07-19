package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.UserDto;
import org.diegodev.hotelalurabackend.models.entities.User;
import org.diegodev.hotelalurabackend.models.enums.RoleType;

@NoArgsConstructor
public class UserDtoMapper {

    private User user;

    public static UserDtoMapper builder() {
        return new UserDtoMapper();
    }

    public UserDtoMapper setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDto build() {
        if (user == null) {
            throw new RuntimeException("User entity in UserDTO is null!");
        }
        boolean isAdmin = user.getRoles().stream().anyMatch(r -> RoleType.ADMIN.getRoleName().equals(r.getName()));
        return new UserDto(
                this.user.getId(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), isAdmin
        );
    }


}
