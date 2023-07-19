package org.diegodev.hotelalurabackend.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {

    DELUXE("DELUXE"),
    LUXURY("LUXURY"),
    SUITE("SUITE");

    private final String roomTypeName;

    @Override
    public String toString() {
        return roomTypeName;
    }
}
