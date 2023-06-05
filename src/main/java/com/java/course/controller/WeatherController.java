package com.java.course.controller;

import java.util.Objects;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {


    private final HistoricalWeatherClient historicalWeatherClient;

    public WeatherController(HistoricalWeatherClient historicalWeatherClient) {
        this.historicalWeatherClient = historicalWeatherClient;
    }

    @GetMapping
    public double getWeather() {
        final WeatherResponse weather = historicalWeatherClient.getWeather(42.149280, 24.741647);
        return weather.getTemperatures().stream()
                .filter(Objects::nonNull)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }
}
