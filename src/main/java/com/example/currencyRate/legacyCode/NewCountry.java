package com.example.currencyRate.legacyCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewCountry {
    String zh;
    String en;
    String alpha3;
    String currency;
    public NewCountry(String zh, String en, String alpha3, String currency){
        this.zh = zh;
        this.en = en;
        this.alpha3 = alpha3;
        this.currency = currency;
    }
    public NewCountry(){}
    public String getEn() {
        return en;
    }

    public String getZh() {
        return zh;
    }

    public String getAlpha3() {
        return alpha3;
    }
    public String getCurrency() {return currency; }

    @Override
    public String toString() {
        return "legacyJson.NewCountry{" +
                "zh='" + zh + '\'' +
                ", en='" + en + '\'' +
                ", alpha3='" + alpha3 + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
