package org.diegodev.hotelalurabackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.HotelDto;
import org.diegodev.hotelalurabackend.models.request.HotelRequest;
import org.diegodev.hotelalurabackend.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotels")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class HotelController extends BaseController {

    private final HotelService service;

    @GetMapping
    public List<HotelDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> show(@PathVariable("id") Long id) {
        Optional<HotelDto> userOptional = service.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody HotelRequest hotel, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<HotelDto> o = service.update(hotel, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<HotelDto> o = service.findById(id);

        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();
    }


}
