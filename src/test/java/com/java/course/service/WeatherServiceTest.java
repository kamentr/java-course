package com.java.course.service;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

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
    void getHistoricalWeatherDataForAllCountries() throws IOException, URISyntaxException {
      weatherService.getHistoricalWeatherDataForAllCountries();
      verify(cityService, times(1)).getAllCapitolCities();
    }

    @Test
    void getHistoricalWeatherDataForAllCountrie2() throws IOException, URISyntaxException {
        List<City> cities = List.of(new City("Sofia", "Sofia", "Bulgaria"));
        when(cityService.getAllCapitolCities()).thenReturn(cities);
        Coordinates coords = new Coordinates(10,11);
        when(locationService.getCoordinates("Sofia")).thenReturn(coords);
        Hourly hourly = new Hourly(List.of(10f, 12f, 13f));
        Daily daily = new Daily(List.of(10f, 12f), List.of(LocalDate.now(), LocalDate.now()));
        WeatherResponse response = new WeatherResponse(hourly, daily);
        when(weatherClient.getWeather(anyDouble(), anyDouble(), any(), any())).thenReturn(response);

        List<List<AverageTempYear>> actualResult = weatherService.getHistoricalWeatherDataForAllCountries();

        assertThat(actualResult.size()).isGreaterThan(0);
    }
}