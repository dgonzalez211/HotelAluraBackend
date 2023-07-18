package org.diegodev.hotelalurabackend.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomStatusTypes {

    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    MISSED("MISSED");

    private final String roomStatusName;

    @Override
    public String toString() {
        return roomStatusName;
    }
}
