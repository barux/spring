package com.barux.e4cSpring.game;

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
    private Long id;
    private String name;
    private String description;
    private Date releaseDate;
    private Float price;
    private Genre genre;
    private Platform platform;
}
