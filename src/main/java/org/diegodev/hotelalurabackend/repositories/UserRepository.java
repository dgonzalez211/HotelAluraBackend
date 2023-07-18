package org.diegodev.hotelalurabackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.diegodev.hotelalurabackend.models.entities.User;

public interface UserRepository
        extends CrudRepository<User, Long> {

        Optional<User> findByUsername(String username);

        Optional<User> findByEmail(String email);

        @Query("select u from User u where u.username=?1")
        Optional<User> getUserByUsername(String username);
}
