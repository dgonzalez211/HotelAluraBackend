package org.diegodev.hotelalurabackend.services.impl;


import org.diegodev.hotelalurabackend.models.dto.ReservationDto;
import org.diegodev.hotelalurabackend.models.dto.mapper.ReservationDtoMapper;
import org.diegodev.hotelalurabackend.models.entities.Hotel;
import org.diegodev.hotelalurabackend.models.entities.Reservation;
import org.diegodev.hotelalurabackend.models.entities.Room;
import org.diegodev.hotelalurabackend.models.entities.User;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;
import org.diegodev.hotelalurabackend.models.request.ReservationRequest;
import org.diegodev.hotelalurabackend.repositories.HotelRepository;
import org.diegodev.hotelalurabackend.repositories.ReservationRepository;
import org.diegodev.hotelalurabackend.repositories.RoomRepository;
import org.diegodev.hotelalurabackend.repositories.UserRepository;
import org.diegodev.hotelalurabackend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Override
    @Transactional
    public List<ReservationDto> findByUser(String username) {
        List<Reservation> reservations = reservationRepository.findAll().stream().filter(
                reservation -> reservation.getUser().getUsername().equals(username)
        ).toList();;
        return reservations
                .stream()
                .map(r -> ReservationDtoMapper.builder().setReservation(r).build())
                .collect(Collectors.toList());
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
    public ReservationDto save(ReservationRequest reservationRequest) throws InvalidParameterException {

        Long roomId = reservationRequest.getRoomId();
        Long userId = reservationRequest.getUserId();
        Long hotelId = reservationRequest.getHotelId();

        Reservation reservation = new Reservation();
        reservation.setCheckIn(reservationRequest.getCheckIn());
        reservation.setCheckOut(reservationRequest.getCheckOut());
        reservation.setGuests(reservationRequest.getGuests());
        reservation.setStatus(reservationRequest.getStatus());

        Room room = roomRepository.findById(roomId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);


        if (room == null || user == null || hotel == null) {
            throw new InvalidParameterException("Invalid Room, User, or Hotel ID");
        }

        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setHotel(hotel);

        Reservation savedReservation = reservationRepository.save(reservation);

        return ReservationDtoMapper.builder().setReservation(reservationRepository.save(savedReservation)).build();
    }

    @Override
    @Transactional
    public Optional<ReservationDto> update(ReservationRequest reservationRequest, Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        Reservation reservationOptional = null;
        if (reservation.isPresent()) {

            Reservation reservationDb = reservation.orElseThrow();
            reservationDb.setCheckIn(reservationRequest.getCheckIn());
            reservationDb.setCheckOut(reservationRequest.getCheckOut());
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