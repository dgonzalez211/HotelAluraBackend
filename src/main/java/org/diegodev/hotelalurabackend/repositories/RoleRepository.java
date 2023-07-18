package org.diegodev.hotelalurabackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.diegodev.hotelalurabackend.models.entities.Role;

public interface RoleRepository
        extends CrudRepository<Role, Long> {
        Optional<Role> findByName(String name);
}
