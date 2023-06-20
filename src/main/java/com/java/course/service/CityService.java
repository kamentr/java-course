package com.java.course.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    public List<String> getAllCapitolCities() throws IOException, URISyntaxException {
        List<City> cities = new ArrayList<>();
        Path path = Paths.get(Main.class.getClassLoader().getResource("cities/worldcities.csv").toURI());

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
                .filter(city -> city.capital().equals("primary"))
                .map(City::name)
                .toList();
    }

    private static final class City {
        private final String name;
        private final String capital;

        private City(String name, String capital) {
            this.name = name;
            this.capital = capital;
        }

        public String name() {
            return name;
        }

        public String capital() {
            return capital;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (City) obj;
            return Objects.equals(this.name, that.name) &&
                    Objects.equals(this.capital, that.capital);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, capital);
        }

        @Override
        public String toString() {
            return "City[" +
                    "name=" + name + ", " +
                    "capital=" + capital + ']';
        }

        }
}
