package org.diegodev.hotelalurabackend.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IRoom;
import org.diegodev.hotelalurabackend.models.enums.RoomType;

@Getter
@Setter
public class RoomRequest implements IRoom {

    @NotBlank(message = "Room name is mandatory")
    private String name;

    @NotEmpty
    private RoomType type;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank
    private String availableFrom;

    @NotBlank
    private String availableTo;

    private boolean available;
}
