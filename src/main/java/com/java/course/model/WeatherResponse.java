package com.java.course.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(Hourly hourly, Daily daily) {

    public List<Float> getDailyTemperatures() {
        return daily.temperatures();
    }

    public List<LocalDate> getTime() {
        return daily.time();
    }
}

