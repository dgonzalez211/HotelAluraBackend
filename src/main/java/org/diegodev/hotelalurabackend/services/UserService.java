package org.diegodev.hotelalurabackend.services;

import java.util.List;
import java.util.Optional;

import org.diegodev.hotelalurabackend.models.dto.UserDto;
import org.diegodev.hotelalurabackend.models.entities.User;
import org.diegodev.hotelalurabackend.models.request.UserRequest;

public interface UserService {
    
    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);
    Optional<UserDto> update(UserRequest user, Long id);

    void remove(Long id);

    boolean emailExists(String email);
    boolean usernameExists(String username);
}
