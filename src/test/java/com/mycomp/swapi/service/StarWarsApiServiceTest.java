package com.mycomp.swapi.service;

import com.mycomp.swapi.request.StarWarsApiPlanetResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StarWarsApiServiceTest {

    @InjectMocks
    private StarWarsApiService starWarsApiService;

    @Test
    @DisplayName("Find by Name list of planet when Successful")
    void getPlanet_ReturnsListOfPlanetsFromApi_WhenSuccessful() {
        StarWarsApiPlanetResult[] planetApi =starWarsApiService.getPlanet("Alderaan");
        Assertions.assertThat(planetApi.length).isEqualTo(1);
    }

    @Test
    @DisplayName("Return a empty list when Not Found")
    void getPlanet_ReturnsEmptyList_WhenNotFound() {
        StarWarsApiPlanetResult[] planetApi =starWarsApiService.getPlanet("NotExists");
        Assertions.assertThat(planetApi.length).isEqualTo(0);
    }
}