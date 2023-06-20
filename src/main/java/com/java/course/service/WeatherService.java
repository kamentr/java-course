package com.java.course.service;

import com.java.course.client.GeocodingClient;
import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.Coordinates;
import com.java.course.model.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherService {

    private final HistoricalWeatherClient weatherClient;
    private final GeocodingClient geocodingClient;
    private final CityService cityService;

    public WeatherService(HistoricalWeatherClient weatherClient, GeocodingClient geocodingClient, CityService cityService) {
        this.weatherClient = weatherClient;
        this.geocodingClient = geocodingClient;
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

    public Coordinates getCoordinates(String location) {
        return geocodingClient.getCoordinates(location);
    }

    public double getAverageWeatherByLocation(String location, LocalDate date) {
        final Coordinates coordinates = getCoordinates(location);
        return getAverageWeather(coordinates.getLatitude(), coordinates.getLongitude(), date, date);
    }

    public List<Double> getHistoricalWeatherDataForLocation(String location) {
        List<Double> yearlyAverageTemps = new ArrayList<>();
        final Coordinates coordinates = getCoordinates(location);

        int startYear = 1941;
        int endYear = 1942;

        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 1, 1);


       while (endDate.getYear()<=2022){
           yearlyAverageTemps.add(getAverageWeather(coordinates.getLatitude(), coordinates.getLongitude(), startDate, endDate));
           endDate = endDate.plusYears(1);
           startDate = startDate.plusYears(1);
        }

        return yearlyAverageTemps;
    }

    public List<List<Double>> getHistoricalWeatherDataForAllCountries() throws IOException, URISyntaxException {
        final List<CityService.City> allCapitals = cityService.getAllCapitolCities();
        return allCapitals.stream()
                .map(city -> getHistoricalWeatherDataForLocation(city.name()))
                .toList();
    }
}
