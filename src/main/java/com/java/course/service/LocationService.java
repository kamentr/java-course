package com.java.course.service;

import com.java.course.client.GeocodingClient;
import com.java.course.model.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final GeocodingClient geocodingClient;

    public LocationService(GeocodingClient geocodingClient) {
        this.geocodingClient = geocodingClient;
    }

    public Coordinates getCoordinates(String location) {
        return geocodingClient.getCoordinates(location);
    }
}
