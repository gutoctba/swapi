package com.mycomp.swapi.service;

import com.mycomp.swapi.request.StarWarsApiPlanetResponse;
import com.mycomp.swapi.request.StarWarsApiPlanetResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class StarWarsApiService {

    public StarWarsApiPlanetResult[] getPlanet(String name) {
        StarWarsApiPlanetResult[] result;
        ResponseEntity<StarWarsApiPlanetResponse> entity =
                new RestTemplate().getForEntity("https://swapi.dev/api/planets/?search={name}"
                        , StarWarsApiPlanetResponse.class, name);
        result = entity.getBody().getResults();
        return result;
    }


}
