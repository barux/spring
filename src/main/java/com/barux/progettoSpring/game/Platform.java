package com.barux.progettoSpring.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Platform {
    PC("PC"),
    PS4("Playstation 4"),
    PS5("Playstation 5"),
    XBOX_ONE("Xbox One"),
    XBOX_SERIES_X("Xbox Series X"),
    SWITCH("Nintendo Switch");

    private final String name;
}
