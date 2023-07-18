package org.diegodev.hotelalurabackend.models.dto.mapper;

import lombok.NoArgsConstructor;
import org.diegodev.hotelalurabackend.models.dto.RoomDto;
import org.diegodev.hotelalurabackend.models.entities.Room;
import org.diegodev.hotelalurabackend.models.entities.Room;
import org.diegodev.hotelalurabackend.models.enums.RoleTypes;

@NoArgsConstructor
public class RoomDtoMapper {

    private Room room;

    public static RoomDtoMapper builder() {
        return new RoomDtoMapper();
    }

    public RoomDtoMapper setRoom(Room room) {
        this.room = room;
        return this;
    }

    public RoomDto build() {
        if (room == null) {
            throw new RuntimeException("Room entity in RoomDTO is null!");
        }

        return new RoomDto(
                this.room.getId(), room.getName(), room.getType(),
                room.getDescription(), room.getAvailableFrom(), room.getAvailableTo(), room.isAvailable()
        );
    }


}
