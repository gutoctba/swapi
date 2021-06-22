package com.mycomp.swapi.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public class StarWarsApiPlanetResponse {

        public StarWarsApiPlanetResponse() {
        }

        private String count;
        private StarWarsApiPlanetResult[] results;

    }
