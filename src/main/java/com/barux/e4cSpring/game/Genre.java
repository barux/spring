package com.barux.e4cSpring.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    FIGHTING("Fighting"),
    MOBA("Moba"),
    PLATFORM("Platform"),
    PUZZLE("Puzzle"),
    RACING("Racing"),
    RPG("RPG"),
    SHOOTER("Shooter"),
    SIMULATION("Simulation"),
    SPORTS("Sports"),
    STRATEGY("Strategy");

    private final String name;
}
