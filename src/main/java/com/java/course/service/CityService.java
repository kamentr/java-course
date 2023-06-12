package com.java.course.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    public List<String> getAllCapitolCities() throws IOException, URISyntaxException {
        List<City> cities = new ArrayList<>();
        Path path = Paths.get(Main.class.getClassLoader().getResource("cities/worldcities.csv").toURI());
        String fileName = "classpath:cities/worldcities.csv";
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] values = line.split(",");
            if (values[0].equals("city")) {
                continue;
            }
            String name = values[0];
            String capitol = values[8];
            cities.add(new City(name, capitol));
        }

        return cities.stream()
                // TODO Fix this filter
//                .filter(city -> city.capital.equals("primary"))
                .map(City::name)
                .toList();
    }

    private record City(String name, String capital) {
    }
}
