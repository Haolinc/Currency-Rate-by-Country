package com.example.currencyRate.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Component
public class MapProcessing {
    public static Map<String, String> countryNameToCurrencyMap = new HashMap<>();
    public static Set<String> countryCurrencySet = new HashSet<>();
}
