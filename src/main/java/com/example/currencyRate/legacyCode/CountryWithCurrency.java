package com.example.currencyRate.legacyCode;

public class CountryWithCurrency {
    String country;
    String currency_code;

    public String getCountry() {
        return country;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    @Override
    public String toString() {
        return "legacyJson.CountryWithCurrency{" +
                "country='" + country + '\'' +
                ", currency_code='" + currency_code + '\'' +
                '}';
    }
}
