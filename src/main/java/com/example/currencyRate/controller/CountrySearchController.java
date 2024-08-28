package com.example.currencyRate.controller;

import com.example.currencyRate.data.MapProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// Controller that provide country options list with given text prefix
// Ex: input text "A", will return a list with "Afghanistan", "Albania", etc
@RestController
public class CountrySearchController {
    @Autowired
    private MapProcessing mapProcessing;
    @GetMapping("/country")
    public List<String> getValidCountryList(@RequestParam String countryName){
        return mapProcessing.countryNameToCurrencyMap.keySet().stream()
                .filter(cn -> cn.toLowerCase().startsWith(countryName.toLowerCase()))
                .sorted()
                .collect(Collectors.toList());
    }

    @GetMapping("/isValidCountry")
    public boolean isValidCountry(@RequestParam String countryName){
        return mapProcessing.countryNameToCurrencyMap.containsKey(countryName);
    }
}
