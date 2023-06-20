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

    @JsonProperty("daily")
    private Daily daily;

    public List<Float> getHourlyTemperatures() {
        return hourly.temperatures;
    }

    public List<Float> getDailyTemperatures() {
        return daily.temperatures;
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

    private static class Daily {
        @JsonProperty("temperature_2m_max")
        private List<Float> temperatures;

        public Daily() {
        }

        public Daily(List<Float> temperatures) {
            this.temperatures = temperatures;
        }
    }
}
