package org.diegodev.hotelalurabackend.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatusType {

    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    MISSED("MISSED");

    private final String reservationStatusName;

    @Override
    public String toString() {
        return reservationStatusName;
    }
}
