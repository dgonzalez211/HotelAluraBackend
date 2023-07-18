package org.diegodev.hotelalurabackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.request.RoomRequest;
import org.diegodev.hotelalurabackend.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class RoomController extends BaseController {

    private final RoomService service;

    @GetMapping
    public List<RoomDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> show(@PathVariable("id") Long id) {
        Optional<RoomDto> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RoomRequest room, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<RoomDto> o = service.update(room, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<RoomDto> o = service.findById(id);

        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();
    }


}
