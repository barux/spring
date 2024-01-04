package com.barux.progettoSpring.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Integer id;
    private String name;
    private String description;
    private Date releaseDate;
    private Float price;
    private Genre genre;
    private Platform platform;
}

// TODO aggiungere controlli di validazione