package org.diegodev.hotelalurabackend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.diegodev.hotelalurabackend.models.enums.ReservationStatusType;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation extends AuditableEntity {

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Min(1)
    @Max(8)
    @Column(nullable = false)
    private Integer guests;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatusType status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

}