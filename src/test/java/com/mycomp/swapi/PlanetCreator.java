package com.mycomp.swapi;

import com.mycomp.swapi.domain.Planet;

import java.util.Optional;

public class PlanetCreator {

    public static Planet createValidPlanet() {
        return Planet.builder()
                .id("1")
                .name("Alderaan")
                .build();
    }

    public static Optional<Planet> createValidOptionalPlanet() {
        return Optional.of(Planet.builder()
                .id("1")
                .name("Alderaan")
                .build());
    }
}
