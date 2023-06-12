package com.java.course.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.java.course.model.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class HistoricalWeatherClient {
    RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(double latitude, double longitude, LocalDate startDate, LocalDate endDate) {
        String startDateString = formatDate(startDate);
        String endDateString = formatDate(endDate);
        String weatherUrl = "https://archive-api.open-meteo.com/v1/archive?latitude=" + latitude + "&longitude=" + longitude + "&start_date=" + startDateString + "&end_date=" + endDateString + "&hourly=temperature_2m";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(weatherUrl, WeatherResponse.class);

        return response.getBody();
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
}
