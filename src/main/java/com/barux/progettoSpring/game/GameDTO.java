package com.barux.progettoSpring.game;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    @Transient
    private Integer id;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name is required")
    private String name;

    @NotBlank(message = "description can't be blank")
    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "release date is required")
    @PastOrPresent(message = "release date must be in the past or in the present")
    private Date releaseDate;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "price must be positive or zero")
    private Float price;

    @NotNull(message = "genre is required")
    private Genre genre;

    @NotNull(message = "platform is required")
    private Platform platform;
}