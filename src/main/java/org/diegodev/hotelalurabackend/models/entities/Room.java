package org.diegodev.hotelalurabackend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.diegodev.hotelalurabackend.models.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room extends AuditableEntity {

    @NotBlank(message = "Room name is mandatory")
    @Size(min = 3, max = 40, message = "Name must be at least 3 characters long")
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @Column
    @NotNull
    private BigDecimal price;

    @NotEmpty
    @Column
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @NotBlank(message = "Description is mandatory")
    @Column
    private String description;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "available_to")
    private LocalDate availableTo;

    @Transient
    @Column(nullable = false)
    private boolean available;
}
