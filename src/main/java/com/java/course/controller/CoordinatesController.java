package com.java.course.controller;

import com.java.course.model.Coordinates;
import com.java.course.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coordinates")
public class CoordinatesController {

    private final LocationService locationService;

    public CoordinatesController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{location}")
    public Coordinates getCoordinates(@PathVariable String location) {
        return locationService.getCoordinates(location);
    }
}
