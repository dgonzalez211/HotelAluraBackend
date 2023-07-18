package org.diegodev.hotelalurabackend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.diegodev.hotelalurabackend.models.enums.RoomTypes;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends AuditableEntity {

    @NotBlank(message = "Room name is mandatory")
    @Size(min = 3, max = 40, message = "Name must be at least 3 characters long")
    @Column()
    private String name;

    @NotEmpty
    @Column
    private RoomTypes type = RoomTypes.DELUXE;

    @NotBlank(message = "Description is mandatory")
    @Column
    private String description;

    @Column(name = "available_from")
    private String availableFrom;

    @Column(name = "available_to")
    private String availableTo;

    @Column(nullable = false)
    private boolean status;
}
