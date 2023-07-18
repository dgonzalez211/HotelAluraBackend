package org.diegodev.hotelalurabackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.UserDto;
import org.diegodev.hotelalurabackend.models.request.UserRequest;
import org.diegodev.hotelalurabackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class UserController extends BaseController {

    private final UserService service;

    @GetMapping
    public List<UserDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable("id") Long id) {
        Optional<UserDto> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserRequest user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<UserDto> o = service.update(user, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<UserDto> o = service.findById(id);

        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();
    }


}
