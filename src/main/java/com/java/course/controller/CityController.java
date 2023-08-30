package com.java.course.controller;

import com.java.course.dto.CityDto;
import com.java.course.model.City;
import com.java.course.service.CityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    @RequestMapping(produces = "application/json", consumes = "application/json")
    public City save(@RequestBody CityDto city) {
        return cityService.save(city);
    }
}
