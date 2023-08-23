package com.java.course.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.java.course.model.City;
import com.java.course.repository.CityRepository;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private static final String CITIES_PATH = "cities/worldcities.csv";

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCapitolCities() throws IOException, URISyntaxException {
        List<City> cities = new ArrayList<>();
        Path path = Paths.get(Main.class.getClassLoader().getResource(CITIES_PATH).toURI());

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] values = line.split(",");
            if (values[0].equals("city")) {
                continue;
            }
            String name = values[0];
            String capital = values[8];
            String country = values[4];
            cities.add(new City(name, capital, country));
        }

        return cities.stream()
                .filter(city -> city.capital().equals("primary"))
                .limit(1) // Limit because of API limitations
                .toList();
    }

    public City save(City cityToSave) {
        return cityRepository.save(cityToSave);
    }
}
