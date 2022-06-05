package ru.alfabank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ExchangeRate {
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;
}
