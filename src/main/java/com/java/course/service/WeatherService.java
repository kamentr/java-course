package com.java.course.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final HistoricalWeatherClient weatherClient;

    public WeatherService(HistoricalWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public double getAverageWeather(float lat, float lon, LocalDate startDate, LocalDate endDate) {
        final WeatherResponse weather = weatherClient.getWeather(lat, lon, startDate, endDate);
        return weather.getTemperatures().stream()
                .filter(Objects::nonNull)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }
}
