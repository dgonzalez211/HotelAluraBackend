package org.diegodev.hotelalurabackend.services.impl;


import org.diegodev.hotelalurabackend.models.dto.ReservationDto;
import org.diegodev.hotelalurabackend.models.dto.mapper.ReservationDtoMapper;
import org.diegodev.hotelalurabackend.models.entities.Reservation;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;
import org.diegodev.hotelalurabackend.models.request.ReservationRequest;
import org.diegodev.hotelalurabackend.repositories.ReservationRepository;
import org.diegodev.hotelalurabackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    @Transactional
    public List<ReservationDto> findByUser(String username) {
        return findAll().stream().filter(
                reservationDto -> reservationDto.getCustomerUsername().equals(username)
        ).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations
                .stream()
                .map(r -> ReservationDtoMapper.builder().setReservation(r).build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getPagedList(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, sortBy);
        Page<Reservation> pagedResult = reservationRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationDto> findById(Long id) {
        return reservationRepository.findById(id).map(r -> ReservationDtoMapper
                .builder()
                .setReservation(r)
                .build());
    }


    @Override
    @Transactional
    public ReservationDto save(Reservation reservation) {
        return ReservationDtoMapper.builder().setReservation(reservationRepository.save(reservation)).build();
    }

    @Override
    @Transactional
    public Optional<ReservationDto> update(ReservationRequest reservationRequest, Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        Reservation reservationOptional = null;
        if (reservation.isPresent()) {

            Reservation reservationDb = reservation.orElseThrow();
            reservationDb.setCheckIn(reservationRequest.getCheckIn());
            reservationDb.setCheckOut(reservationRequest.getCheckout());
            reservationDb.setGuests(reservationRequest.getGuests());
            reservationDb.setStatus(reservationRequest.getStatus());
            reservationOptional = reservationRepository.save(reservationDb);
        }
        return Optional.ofNullable(ReservationDtoMapper.builder().setReservation(reservationOptional).build());
    }

    @Override
    public boolean overlaps(Reservation from, Reservation to) {
        return from.getCheckIn().isBefore(to.getCheckOut()) && from.getCheckOut().isAfter(to.getCheckIn());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public boolean reservationExists(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.isPresent() && reservation.get().getStatus().equals(ReservationStatusType.ACTIVE);
    }

}