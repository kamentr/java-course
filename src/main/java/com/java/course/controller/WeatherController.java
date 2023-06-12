package com.java.course.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import com.java.course.model.Coordinates;
import com.java.course.service.CityService;
import com.java.course.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final CityService cityService;

    public WeatherController(WeatherService weatherService, CityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
    }

    @GetMapping
    public double getWeather(@RequestParam("lat") float latitude, @RequestParam("lon") float longitude, @RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate) {
        return weatherService.getAverageWeather(latitude, longitude, startDate, endDate);
    }

    @GetMapping("/coordinates/{location}")
    public Coordinates getCoordinates(@PathVariable String location) {
        return weatherService.getCoordinates(location);
    }

    @GetMapping("/cities")
    public List<String> getCapitalCites() throws IOException, URISyntaxException {
        return cityService.getAllCapitolCities();
    }
}
