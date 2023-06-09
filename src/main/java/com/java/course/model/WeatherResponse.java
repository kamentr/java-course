package com.java.course.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(Hourly hourly, Daily daily) {

    public List<Float> getDailyTemperatures() {
        return daily.temperatures();
    }

    public List<LocalDate> getTime() {
        return daily.time();
    }
}

record Hourly(@JsonProperty("temperature_2m") List<Float> temperatures) {
}

record Daily(@JsonProperty("temperature_2m_max") List<Float> temperatures, List<LocalDate> time) {
}
