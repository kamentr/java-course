package com.java.course.model;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String capital;
    @Column
    private String country;

    public City(String name, String capital, String country) {
        this.name = name;
        this.capital = capital;
        this.country = country;
    }

    public City() {

    }

    public String name() {
        return name;
    }

    public String capital() {
        return capital;
    }

    public String country() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (City) obj;
        return Objects.equals(this.name, that.name) && Objects.equals(this.capital, that.capital) && Objects.equals(this.country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capital, country);
    }

    @Override
    public String toString() {
        return "City[" + "name=" + name + ", " + "capital=" + capital + ", " + "country=" + country + ']';
    }

}
