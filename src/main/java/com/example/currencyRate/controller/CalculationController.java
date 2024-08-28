package com.example.currencyRate.controller;

import com.example.currencyRate.data.CurrencyRate;
import com.example.currencyRate.data.MapProcessing;
import com.example.currencyRate.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

// This controller is to calculate the target country currency rate with given input number
@RestController
public class CalculationController {
    @Autowired
    private CurrencyRateService currencyRateService;
    @Autowired
    private MapProcessing mapProcessing;

    @GetMapping("/currencyAmount")
    public BigDecimal getCurrencyRate (@RequestParam String country1, @RequestParam String country2, @RequestParam BigDecimal inputRate) {
        CurrencyRate rate = currencyRateService
                .fetchCurrencyRate(
                        mapProcessing.countryNameToCurrencyMap.get(country1), mapProcessing.countryNameToCurrencyMap.get(country2)
                );
        return inputRate.multiply(rate.getRate()).setScale(2, RoundingMode.CEILING);
    }
}
