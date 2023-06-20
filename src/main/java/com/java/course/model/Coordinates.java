package com.java.course.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Coordinates {

    private float latitude;
    private float longitude;

    public Coordinates(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
