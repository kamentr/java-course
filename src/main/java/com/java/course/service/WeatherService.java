package com.java.course.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.java.course.client.HistoricalWeatherClient;
import com.java.course.model.AverageTempYear;
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

    public List<List<AverageTempYear>> getHistoricalWeatherDataForAllCountries() throws IOException, URISyntaxException {
        final List<City> allCapitals = cityService.getAllCapitolCities();
        return allCapitals.stream()
                .map(city -> getHistoricalWeatherDataForLocation(city.name()))
                .toList();
    }

    public List<AverageTempYear> getHistoricalWeatherDataForLocation(String location) {
        var yearlyAverageTemps = new ArrayList<AverageTempYear>();
        final Coordinates coordinates = locationService.getCoordinates(location);

        int startYear = 1941;
        int endYear = 1942;

        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 1, 1);


        while (endDate.getYear() <= 2022) {
            final double averageTemp = getAverageWeather(coordinates.latitude(), coordinates.longitude(), startDate, endDate);
            yearlyAverageTemps.add(new AverageTempYear(String.valueOf(startDate.getYear()), averageTemp));
            endDate = endDate.plusYears(1);
            startDate = startDate.plusYears(1);
        }

        return yearlyAverageTemps;
    }

    public List<Double> getHistoricalWeatherDataPerMonth(String monthNumber, String location) {
        final Coordinates coordinates = locationService.getCoordinates(location);
        LocalDate startDate = LocalDate.of(1941, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 31);
        final WeatherResponse averageWeather = weatherClient.getWeather(coordinates.latitude(), coordinates.longitude(), startDate, endDate);

        final List<Float> dailyTemperatures = averageWeather.getDailyTemperatures();
        final List<LocalDate> dates = averageWeather.getTime();

        List<Double> averageMonthlyTempsPerYear = new ArrayList<>();

        for (int year = 1941; year <= 2022; year++) {
            averageMonthlyTempsPerYear.add(getAverageTempsForMonth(dailyTemperatures, dates, Integer.parseInt(monthNumber), year));
        }

        return averageMonthlyTempsPerYear;
    }

    private Double getAverageTempsForMonth(List<Float> dailyTemperatures, List<LocalDate> dates, int monthNumber, int year) {
        Map<LocalDate, Float> dateToTemp = new HashMap<>();
        for (int i = 0; i < dates.size(); i++) {
            dateToTemp.put(dates.get(i), dailyTemperatures.get(i));
        }

        return dateToTemp.entrySet().stream()
                .filter(entry -> entry.getKey().getYear() == year)
                .filter(entry -> entry.getKey().getMonthValue() == monthNumber)
                .mapToDouble(Map.Entry::getValue)
                .average()
                .orElse(0);
    }
}
