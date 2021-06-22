package com.mycomp.swapi.controller;

import com.mycomp.swapi.domain.Planet;
import com.mycomp.swapi.exceptions.BadRequestException;
import com.mycomp.swapi.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    public ResponseEntity<List<Planet>> list() {
        return ResponseEntity.ok(planetService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Planet> findById(@PathVariable String id) {
        return ResponseEntity.ok(planetService.findById(id));
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<Planet> findByName(@RequestParam String name) {
        return ResponseEntity.ok(planetService.findByName(name).get());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        planetService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Planet> save(@RequestBody @Valid Planet planet){
        return new ResponseEntity<>(planetService.save(planet), HttpStatus.CREATED);
    }
}
