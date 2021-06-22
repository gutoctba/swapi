package com.mycomp.swapi.service;

import com.mycomp.swapi.PlanetCreator;
import com.mycomp.swapi.domain.Planet;
import com.mycomp.swapi.repository.PlanetRepository;
import com.mycomp.swapi.request.StarWarsApiPlanetResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private StarWarsApiService starWarsAPIServiceMock;

    @Mock
    private PlanetRepository planetRepositoryMock;

    @Test
    @DisplayName("list returns the list of all planets when successful")
    public void list_ReturnListOfAllPlanets_When_Successful() {
        String expectedName = PlanetCreator.createValidPlanet().getName();

        List<Planet> planets = planetService.listAll();

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
        Planet savedPlanet = this.planetService.save(planet);

        Assertions.assertThat(savedPlanet).isNotNull().isEqualTo(planet);
    }

    @Test
    @DisplayName("findByName returns a list of Planet when successful")
    void findByName_ReturnsListOfPlanet_WhenSuccessful(){
        String expectedName = PlanetCreator.createValidPlanet().getName();

        Planet planetFound = planetService.findByName("Alderaan").get();
        Assertions.assertThat(planetFound.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns a list of Planet when successful")
    void findById_ReturnsListOfPlanet_WhenSuccessful(){
        String expectedName = PlanetCreator.createValidPlanet().getName();

        Planet planetFound = planetService.findByName("Alderaan").get();
        Assertions.assertThat(planetFound.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("delete remove planet when successful")
    void delete_RemovePlanet_WhenSuccessful(){
        Assertions.assertThatCode(() -> planetService.delete("1"))
                .doesNotThrowAnyException();

        planetService.delete("1");

        Assertions.assertThatCode(() -> planetService.delete("1"))
                .doesNotThrowAnyException();
    }

    @BeforeEach
    void setUp(){
        BDDMockito.when(planetRepositoryMock.findAll())
                .thenReturn(List.of(PlanetCreator.createValidPlanet()));

        BDDMockito.when(planetRepositoryMock.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(PlanetCreator.createValidPlanet()));

        BDDMockito.when(planetRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(PlanetCreator.createValidOptionalPlanet());

        BDDMockito.when(planetRepositoryMock.save(ArgumentMatchers.any(Planet.class)))
                .thenReturn(PlanetCreator.createValidPlanet());

        BDDMockito.doNothing().when(planetRepositoryMock).delete(ArgumentMatchers.any(Planet.class));

        BDDMockito.when(starWarsAPIServiceMock.getPlanet(ArgumentMatchers.anyString()))
                .thenReturn(new StarWarsApiPlanetResult[0]);
    }
}