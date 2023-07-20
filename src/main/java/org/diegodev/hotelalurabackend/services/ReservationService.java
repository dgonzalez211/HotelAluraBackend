package org.diegodev.hotelalurabackend.services;

import org.diegodev.hotelalurabackend.models.dto.ReservationDto;
import org.diegodev.hotelalurabackend.models.entities.Reservation;
import org.diegodev.hotelalurabackend.models.request.ReservationRequest;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<ReservationDto> findAll();

    List<ReservationDto> findByUser(String username);

    Optional<ReservationDto> findById(Long id);

    List<Reservation> getPagedList(int pageNo, int pageSize, String sortBy);

    ReservationDto save(Reservation reservation);

    ReservationDto save(ReservationRequest reservationRequest);

    void remove(Long id);

    Optional<ReservationDto> update(ReservationRequest user, Long id);

    boolean overlaps(Reservation from, Reservation to);

    boolean reservationExists(Long id);
}