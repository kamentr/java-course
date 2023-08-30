package com.java.course.controller;

import com.java.course.model.City;
import com.java.course.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @RequestMapping(produces = "application/json", consumes = "application/json")
    public City save(@RequestBody City city) {
        return cityService.save(city);
    }

    @GetMapping("/sync")
    public ResponseEntity<Object> sync() throws IOException, URISyntaxException { cityService.sync();
        return ResponseEntity.ok().build();
    }
}
