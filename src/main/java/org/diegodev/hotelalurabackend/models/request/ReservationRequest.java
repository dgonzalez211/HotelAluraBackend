package org.diegodev.hotelalurabackend.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IReservation;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest implements IReservation {

    @NotBlank
    private LocalDate checkIn;

    @NotBlank
    private LocalDate checkout;

    @NotEmpty
    private Integer guests;

    @NotEmpty
    private ReservationStatusType status;
}
