package com.java.course.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.City;
import com.java.course.model.Coordinates;
import com.java.course.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final HistoricalWeatherClient weatherClient;
    private final LocationService locationService;
    private final CityService cityService;

    public WeatherService(HistoricalWeatherClient weatherClient, LocationService locationService, CityService cityService) {
        this.weatherClient = weatherClient;
        this.locationService = locationService;
        this.cityService = cityService;
    }

    public double getAverageWeather(float lat, float lon, LocalDate startDate, LocalDate endDate) {
        final WeatherResponse weather = weatherClient.getWeather(lat, lon, startDate, endDate);
        return weather.getDailyTemperatures().stream()
                .filter(Objects::nonNull)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    public double getAverageWeatherByLocation(String location, LocalDate date) {
        final Coordinates coordinates = locationService.getCoordinates(location);
        return getAverageWeather(coordinates.latitude(), coordinates.longitude(), date, date);
    }

    public List<List<Double>> getHistoricalWeatherDataForAllCountries() throws IOException, URISyntaxException {
        final List<City> allCapitals = cityService.getAllCapitolCities();
        return allCapitals.stream()
                .map(city -> getHistoricalWeatherDataForLocation(city.name()))
                .toList();
    }

    private List<Double> getHistoricalWeatherDataForLocation(String location) {
        List<Double> yearlyAverageTemps = new ArrayList<>();
        final Coordinates coordinates = locationService.getCoordinates(location);

        int startYear = 1941;
        int endYear = 1942;

        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 1, 1);


        while (endDate.getYear() <= 2022) {
            yearlyAverageTemps.add(getAverageWeather(coordinates.latitude(), coordinates.longitude(), startDate, endDate));
            endDate = endDate.plusYears(1);
            startDate = startDate.plusYears(1);
        }

        return yearlyAverageTemps;
    }
}
