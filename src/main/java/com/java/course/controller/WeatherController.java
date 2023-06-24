package com.java.course.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

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

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public double getWeatherByCoordinates(@RequestParam("lat") float latitude, @RequestParam("lon") float longitude, @RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate) {
        return weatherService.getAverageWeather(latitude, longitude, startDate, endDate);
    }

    @GetMapping("/{location}")
    public double getWeatherByLocationName(@RequestParam("date") LocalDate date, @PathVariable String location) {
        return weatherService.getAverageWeatherByLocation(location, date);
    }


    @GetMapping("/archive")
    public List<List<Double>> getHistoricalData() throws IOException, URISyntaxException {
        return weatherService.getHistoricalWeatherDataForAllCountries();
    }
}
