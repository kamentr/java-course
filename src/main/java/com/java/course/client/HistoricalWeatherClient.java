package com.java.course.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HistoricalWeatherClient {
    public String getWeather(double latitude, double longitude) {
        String weatherUrl = "https://archive-api.open-meteo.com/v1/archive?latitude=" + latitude + "&longitude="+ longitude + "&start_date=2023-05-17&end_date=2023-05-31&hourly=temperature_2m";
        ResponseEntity<String> response = restTemplate.getForEntity(weatherUrl, String.class);

        return response.getBody();
    }

    RestTemplate restTemplate = new RestTemplate();

}
