package org.diegodev.hotelalurabackend.controllers;

import jakarta.validation.Valid;
import org.diegodev.hotelalurabackend.models.entities.User;
import org.diegodev.hotelalurabackend.repositories.RoleRepository;
import org.diegodev.hotelalurabackend.repositories.UserRepository;
import org.diegodev.hotelalurabackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return validation(result);
        }

        // add check for username exists in a DB
        if (service.usernameExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already in use");
        }

        if (service.emailExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This email is already in use");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
}