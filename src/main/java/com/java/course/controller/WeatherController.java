package com.java.course.controller;

import com.java.course.model.Coordinates;
import com.java.course.service.CityService;
import com.java.course.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

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
    public double getWeatherByCoordinates(@RequestParam("lat") float latitude, @RequestParam("lon") float longitude, @RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate) {
        return weatherService.getAverageWeather(latitude, longitude, startDate, endDate);
    }

    @GetMapping("/{location}")
    public double getWeatherByLocationName(@RequestParam("date") LocalDate date, @PathVariable String location) {
        return weatherService.getAverageWeatherByLocation(location, date);
    }

    @GetMapping("/coordinates/{location}")
    public Coordinates getCoordinates(@PathVariable String location) {
        return weatherService.getCoordinates(location);
    }

    @GetMapping("/archive")
    public List<List<Double>> getHistoricalData() throws IOException, URISyntaxException {
        return weatherService.getHistoricalWeatherDataForAllCountries();
    }

    @GetMapping("/cities")
    public List<CityService.City> getCapitalCites() throws IOException, URISyntaxException {
        return cityService.getAllCapitolCities();
    }
}
