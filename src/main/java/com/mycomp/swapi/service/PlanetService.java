package com.mycomp.swapi.service;

import com.mycomp.swapi.exceptions.BadRequestException;
import com.mycomp.swapi.repository.PlanetRepository;
import com.mycomp.swapi.request.StarWarsApiPlanetResult;
import com.mycomp.swapi.domain.Planet;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PlanetService {

    @Autowired
    StarWarsApiService starWarsAPIService;

    @Autowired
    PlanetRepository planetRepository;

    public List<Planet> listAll() {
        return planetRepository.findAll();
    }

    public void delete(String id) {
        planetRepository.delete(findById(id));
    }

    public Planet save(Planet planet) {
        planet.setFilms(getNumberOfFilms(planet.getName()));
        Optional<Planet> saved = findByName(planet.getName());
        if(saved.isPresent()){
            planet.setId(saved.get().getId());
        }
        return planetRepository.save(planet);
    }

    public Planet findById(String id) {
        return planetRepository.findById(new ObjectId(id)).orElseThrow(() -> new BadRequestException("Planet not found"));
    }

    public Optional<Planet> findByName(String name) {
        Optional<Planet> planet = planetRepository.findByName(name);
        if(planet.isPresent()) {
            planet.get().setFilms(getNumberOfFilms(name));
        }
        return planet;
    }

    private int getNumberOfFilms(String planetName) {
        StarWarsApiPlanetResult[] pr = starWarsAPIService.getPlanet(planetName);
        return pr.length>0?pr[0].getFilms().length:0;
    }

}
