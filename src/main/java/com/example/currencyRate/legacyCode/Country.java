package com.example.currencyRate.legacyCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    String zh;
    String en;
    String alpha3;
    public String getEn() {
        return en;
    }

    public String getZh() {
        return zh;
    }

    public String getAlpha3() {
        return alpha3;
    }

    @Override
    public String toString() {
        return "legacyJson.Country [en=" + en + ", zh=" + zh + "]";
    }
}
