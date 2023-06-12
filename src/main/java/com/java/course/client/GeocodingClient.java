package com.java.course.client;

import java.util.List;

import com.java.course.model.Coordinates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeocodingClient {

    RestTemplate restTemplate = new RestTemplate();

    public Coordinates getCoordinates(String locationName) {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName + "&count=1";

        final ResponseEntity<GeocodingResponse> response = restTemplate.getForEntity(url, GeocodingResponse.class);
        return response.getBody().getResults().get(0);
    }


    @Getter
    @NoArgsConstructor
    private static class GeocodingResponse {
        private List<Coordinates> results;
    }
}
