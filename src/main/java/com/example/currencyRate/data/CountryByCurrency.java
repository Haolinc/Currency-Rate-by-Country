package com.example.currencyRate.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryByCurrency {
    private String en;
    private String currency;

    @Override
    public String toString() {
        return "Country{" +
                "en='" + en + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}