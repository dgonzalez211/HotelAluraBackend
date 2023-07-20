package org.diegodev.hotelalurabackend.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IReservation;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest implements IReservation {

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    @NotNull
    private Integer guests;

    @NotNull
    private ReservationStatusType status;

    @NotNull
    private Long userId;

    @NotNull
    private Long hotelId;

    @NotNull
    private Long roomId;
}
