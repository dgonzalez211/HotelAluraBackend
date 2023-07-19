package org.diegodev.hotelalurabackend.models.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IHotel;

@Getter
@Setter
public class HotelRequest implements IHotel {

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
}
