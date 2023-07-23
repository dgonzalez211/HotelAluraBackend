package org.diegodev.hotelalurabackend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {

    private Long id;

    @NotNull
    @NotEmpty
    private LocalDate checkIn;

    @NotNull
    @NotEmpty
    private LocalDate checkOut;

    @NotBlank
    private Integer guests;

    @NotNull
    @NotBlank
    private ReservationStatusType status;

    @NotBlank
    private RoomDto room;

    @NotNull
    private HotelDto hotel;

    @NotNull
    private Long userId;

}
