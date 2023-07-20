package org.diegodev.hotelalurabackend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private BigDecimal price;

    @NotNull
    @NotEmpty
    private RoomType type;

    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    private LocalDate availableFrom;

    @NotNull
    @NotEmpty
    private LocalDate availableTo;

}
