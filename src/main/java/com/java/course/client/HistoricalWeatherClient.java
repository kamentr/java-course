package com.java.course.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.java.course.model.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class HistoricalWeatherClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(double latitude, double longitude, LocalDate startDate, LocalDate endDate) {
        String startDateString = formatDate(startDate);
        String endDateString = formatDate(endDate);
        String weatherUrl = "https://archive-api.open-meteo.com/v1/archive?latitude=" + latitude + "&longitude=" + longitude + "&start_date=" + startDateString + "&end_date=" + endDateString + "&daily=temperature_2m_max&timezone=GMT";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(weatherUrl, WeatherResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException(String.format("Failed to obtain weather data for %s-%S with status code: %s and message: %s", latitude, longitude, response.getStatusCode(), response.getBody()));
        }

        return response.getBody();
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
