package com.mycomp.swapi.repository;

import com.mycomp.swapi.domain.Planet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    public Optional<Planet> findByName(String name);
    public Optional<Planet> findById(ObjectId name);
}
