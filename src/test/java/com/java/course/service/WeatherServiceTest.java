package com.java.course.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.Daily;
import com.java.course.model.Hourly;
import com.java.course.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WeatherServiceTest {

    @Mock
    private HistoricalWeatherClient weatherClient;

    @Mock
    private LocationService locationService;

    @Mock
    private CityService cityService;

    WeatherService weatherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(weatherClient, locationService, cityService);
    }

    @Test
    void getAverageWeather_whenInvalidStartDate_shouldFail() {
        float lat = 1;
        float lon = 2;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        Hourly hourly = new Hourly(List.of(10f, 12f, 13f));
        Daily daily = new Daily(List.of(10f, 12f), List.of(LocalDate.now(), LocalDate.now()));
        WeatherResponse response = new WeatherResponse(hourly, daily);
        when(weatherClient.getWeather(lat, lon, startDate, endDate)).thenReturn(response);

        final double actualAverageWeather = weatherService.getAverageWeather(lat, lon, startDate, endDate);

        assertThat(actualAverageWeather).isNotNull();
        assertThat(actualAverageWeather).isEqualTo(11);
        verify(weatherClient, times(1)).getWeather(lat, lon, startDate, endDate);
    }

    @Test
    void getAverageWeatherByLocation() {
    }

    @Test
    void getHistoricalWeatherDataForAllCountries() {
    }
}