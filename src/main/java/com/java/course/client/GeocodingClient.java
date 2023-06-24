package com.java.course.client;

import java.util.List;
import java.util.Objects;

import com.java.course.model.Coordinates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeocodingClient {

    RestTemplate restTemplate = new RestTemplate();

    public Coordinates getCoordinates(String locationName) {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName + "&count=1";

        final ResponseEntity<GeocodingResponse> response = restTemplate.getForEntity(url, GeocodingResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException(String.format("Failed to obtain coordinates of %s with status code: %s and message: %s", locationName, response.getStatusCode(), response.getBody()));
        }

        return Objects.requireNonNull(response.getBody()).results().get(0);
    }

    private record GeocodingResponse(List<Coordinates> results) {
    }
}
