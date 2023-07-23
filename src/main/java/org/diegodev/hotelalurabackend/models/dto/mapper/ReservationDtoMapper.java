package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.HotelDto;
import org.diegodev.hotelalurabackend.models.dto.ReservationDto;
import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.entities.Reservation;

@NoArgsConstructor
public class ReservationDtoMapper {

    private Reservation reservation;

    public static ReservationDtoMapper builder() {
        return new ReservationDtoMapper();
    }

    public ReservationDtoMapper setReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    public ReservationDto build() {
        if (reservation == null) {
            throw new RuntimeException("Reservation entity in ReservationDTO is null!");
        }

        HotelDto hotelDto = HotelDtoMapper.builder().setHotel(reservation.getHotel()).build();
        RoomDto roomDto = RoomDtoMapper.builder().setRoom(reservation.getRoom()).build();
        return new ReservationDto(
                reservation.getId(), reservation.getCheckIn(), reservation.getCheckOut(),
                reservation.getGuests(), reservation.getStatus(), roomDto,
                hotelDto, reservation.getUser().getId()
        );
    }


}
