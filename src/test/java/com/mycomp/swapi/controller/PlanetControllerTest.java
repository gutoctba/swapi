package com.mycomp.swapi.controller;

import com.mycomp.swapi.PlanetCreator;
import com.mycomp.swapi.domain.Planet;
import com.mycomp.swapi.service.PlanetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class PlanetControllerTest {
    @InjectMocks
    private PlanetController planetController;

    @Mock
    private PlanetService planetServiceMock;

    @Test
    @DisplayName("list returns the list of all planets when successful")
    public void list_ReturnListOfAllPlanets_When_Successful() {
        String expectedName = PlanetCreator.createValidPlanet().getName();

        List<Planet> planets = planetController.list().getBody();

        Assertions.assertThat(planets)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(planets.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save returns planet when successful")
    void save_ReturnsPlanet_WhenSuccessful(){
        Planet planet = PlanetCreator.createValidPlanet();
        Planet savedPlanet = this.planetController.save(planet).getBody();

        Assertions.assertThat(savedPlanet).isNotNull().isEqualTo(planet);
    }

    @Test
    @DisplayName("findByName returns a list of Planet when successful")
    void findByName_ReturnsListOfPlanet_WhenSuccessful(){
        String expectedName = PlanetCreator.createValidPlanet().getName();

        Planet planetFound = planetController.findByName("Alderaan").getBody();
        Assertions.assertThat(planetFound.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns a list of Planet when successful")
    void findById_ReturnsListOfPlanet_WhenSuccessful(){
        String expectedName = PlanetCreator.createValidPlanet().getName();

        Planet planetFound = planetController.findByName("Alderaan").getBody();
        Assertions.assertThat(planetFound.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("delete remove planet when successful")
    void delete_RemovePlanet_WhenSuccessful(){
        Assertions.assertThatCode(() ->planetController.delete("1"))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = planetController.delete("1");

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @BeforeEach
    void setUp(){
        BDDMockito.when(planetServiceMock.listAll())
                .thenReturn(List.of(PlanetCreator.createValidPlanet()));

        BDDMockito.when(planetServiceMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(PlanetCreator.createValidPlanet());

        BDDMockito.when(planetServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(PlanetCreator.createValidOptionalPlanet());

        BDDMockito.when(planetServiceMock.save(ArgumentMatchers.any(Planet.class)))
                .thenReturn(PlanetCreator.createValidPlanet());

        BDDMockito.doNothing().when(planetServiceMock).delete(ArgumentMatchers.anyString());
    }

}