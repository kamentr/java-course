package com.java.course.client;

import com.java.course.model.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class HistoricalWeatherClient {
    RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(double latitude, double longitude) {
        String weatherUrl = "https://archive-api.open-meteo.com/v1/archive?latitude=" + latitude + "&longitude=" + longitude + "&start_date=2023-06-05&end_date=2023-06-05&hourly=temperature_2m";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(weatherUrl, WeatherResponse.class);

        return response.getBody();
    }
}
