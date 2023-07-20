package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.HotelDto;
import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.entities.Hotel;

import java.util.List;

@NoArgsConstructor
public class HotelDtoMapper {

    private Hotel hotel;

    public static HotelDtoMapper builder() {
        return new HotelDtoMapper();
    }

    public HotelDtoMapper setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public HotelDto build() {
        if (hotel == null) {
            throw new RuntimeException("Hotel entity in HotelDTO is null!");
        }
        List<RoomDto> roomDtos = RoomDtoMapper.builder().setRooms(hotel.getRooms()).buildAll();
        return new HotelDto(
                hotel.getId(), hotel.getName(), hotel.getEmail(), hotel.getPhoneNumber(),
                hotel.getTelephoneNumber(), hotel.getAddress(), hotel.getLocation(), hotel.getRating(), roomDtos
        );
    }


}
