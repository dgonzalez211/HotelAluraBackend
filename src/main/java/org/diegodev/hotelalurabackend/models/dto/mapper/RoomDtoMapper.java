package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.entities.Room;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RoomDtoMapper {

    private Room room;

    private List<Room> rooms;

    public static RoomDtoMapper builder() {
        return new RoomDtoMapper();
    }

    public RoomDtoMapper setRoom(Room room) {
        this.room = room;
        return this;
    }

    public RoomDtoMapper setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public RoomDto build() {
        if (room == null) {
            throw new RuntimeException("Room entity in RoomDTO is null!");
        }

        return new RoomDto(
                this.room.getId(), room.getName(), room.getPrice(), room.getType(),
                room.getDescription(), room.getAvailableFrom(), room.getAvailableTo()
        );
    }

    public List<RoomDto> buildAll() {
        if (rooms.isEmpty()) {
            throw new RuntimeException("Rooms list entity in RoomDTO is null!");
        }

        List<RoomDto> roomDtos = new ArrayList<>();

        for (Room room : this.rooms) {
            roomDtos.add(new RoomDto(
                    room.getId(), room.getName(), room.getPrice(), room.getType(),
                    room.getDescription(), room.getAvailableFrom(), room.getAvailableTo()
            ));
        }

        return roomDtos;
    }


}
