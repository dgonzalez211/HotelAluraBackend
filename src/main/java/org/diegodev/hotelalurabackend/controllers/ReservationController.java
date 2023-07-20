package org.diegodev.hotelalurabackend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.ReservationDto;
import org.diegodev.hotelalurabackend.models.request.ReservationRequest;
import org.diegodev.hotelalurabackend.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class ReservationController extends BaseController {

    private final ReservationService service;

    @GetMapping
    public List<ReservationDto> list() {
        return service.findAll();
    }

    @GetMapping("/user")
    public ResponseEntity<List<ReservationDto>> userReservations(@RequestParam(name = "username") String username) {
        List<ReservationDto> reservations = service.findByUser(username);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> show(@PathVariable("id") Long id) {
        Optional<ReservationDto> reservationOptional = service.findById(id);

        if (reservationOptional.isPresent()) {
            return ResponseEntity.ok(reservationOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ReservationRequest reservationRequest, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(reservationRequest));
        } catch (InvalidParameterException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ReservationRequest Reservation, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ReservationDto> o = service.update(Reservation, id);

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ReservationDto> o = service.findById(id);

        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();
    }


}
