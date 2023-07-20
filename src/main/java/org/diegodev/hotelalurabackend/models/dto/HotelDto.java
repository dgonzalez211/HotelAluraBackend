package org.diegodev.hotelalurabackend.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.entities.Room;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String telephoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String location;

    @NotNull
    private Double rating;

    @NotEmpty
    private List<RoomDto> rooms;
}
