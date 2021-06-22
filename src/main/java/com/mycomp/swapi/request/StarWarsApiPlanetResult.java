package com.mycomp.swapi.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsApiPlanetResult {
    private String name;
    private String[] films;
}
