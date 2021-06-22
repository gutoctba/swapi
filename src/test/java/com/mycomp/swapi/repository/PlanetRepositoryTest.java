package com.mycomp.swapi.repository;

import com.mycomp.swapi.PlanetCreator;
import com.mycomp.swapi.domain.Planet;
import org.assertj.core.api.Assertions;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    @DisplayName("Save persists Planet when Successful")
    void save_PersistPlanet_WhenSuccessful() {
        Planet Planet = PlanetCreator.createValidPlanet();
        Planet savedPlanet = this.planetRepository.save(Planet);
        Assertions.assertThat(savedPlanet).isNotNull();
        Assertions.assertThat(savedPlanet.getId()).isNotNull();
        Assertions.assertThat(savedPlanet.getName()).isEqualTo(Planet.getName());
    }

    @Test
    @DisplayName("Save updates planet when Successful")
    void save_UpdatesPlanet_WhenSuccessful() {
        Planet Planet = PlanetCreator.createValidPlanet();
        Planet savedPlanet = this.planetRepository.save(Planet);
        savedPlanet.setName("TatooineUpdated");
        Planet planetUpdated = planetRepository.save(savedPlanet);
        Assertions.assertThat(planetUpdated).isNotNull();
        Assertions.assertThat(planetUpdated.getId()).isNotNull();
        Assertions.assertThat(savedPlanet.getName()).isEqualTo(planetUpdated.getName());
    }

    @Test
    @DisplayName("Save removes planet when Successful")
    void save_RemovesPlanet_WhenSuccessful() {
        Planet planet = PlanetCreator.createValidPlanet();
        Planet savedPlanet = planetRepository.save(planet);
        planetRepository.delete(savedPlanet);
        Optional<Planet> planetOptional = planetRepository.findById(new ObjectId(savedPlanet.getId()));
        Assertions.assertThat(planetOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by Name list of planet when Successful")
    void findByName_ReturnsListOfPlanet_WhenSuccessful() {
        Planet planet = PlanetCreator.createValidPlanet();
        Planet savedPlanet = planetRepository.save(planet);
        String name = savedPlanet.getName();
        Planet byName = planetRepository.findByName(name).get();
        Assertions.assertThat(byName.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("Find by Name empty list when no planet is found")
    void findByName_ReturnsListOfPlanet_WhenPlanetIsNotFound() {
        Planet byName = planetRepository.findByName("notExistsName").get();
        Assertions.assertThat(byName).isNull();
    }

}