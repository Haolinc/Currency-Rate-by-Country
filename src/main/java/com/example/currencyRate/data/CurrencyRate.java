package com.example.currencyRate.data;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CurrencyRate {
    private final String sourceCountryName;
    private final String targetCountryName;
    private final BigDecimal rate;
    private final String date;

    public CurrencyRate(String sourceCountryName, String targetCountryName, BigDecimal rate, String date) {
        this.sourceCountryName = sourceCountryName;
        this.targetCountryName = targetCountryName;
        this.rate = rate;
        this.date = date;
    }

    @Override
    public String toString() {
        return "data.CurrencyRate{" +
                "sourceCountryName='" + sourceCountryName + '\'' +
                ", targetCountryName='" + targetCountryName + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                '}';
    }
}
