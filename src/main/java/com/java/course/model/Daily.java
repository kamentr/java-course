package com.java.course.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Daily(@JsonProperty("temperature_2m_max") List<Float> temperatures, List<LocalDate> time) {
}
