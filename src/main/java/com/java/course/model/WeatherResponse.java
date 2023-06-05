package com.java.course.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    public WeatherResponse() {
    }

    @JsonProperty("hourly")
    private Hourly hourly;

    public List<Float> getTemperatures() {
        return hourly.temperatures;
    }

    private static class Hourly {
        @JsonProperty("temperature_2m")
        private List<Float> temperatures;

        public Hourly() {
        }

        private Hourly(List<Float> temperatures) {
            this.temperatures = temperatures;
        }
    }
}
