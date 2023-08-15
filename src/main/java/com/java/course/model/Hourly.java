package com.java.course.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Hourly(@JsonProperty("temperature_2m") List<Float> temperatures) {
}
