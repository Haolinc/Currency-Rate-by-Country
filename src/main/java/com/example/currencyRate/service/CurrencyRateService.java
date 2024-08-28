package com.example.currencyRate.service;

import com.example.currencyRate.data.CurrencyRate;
import com.example.currencyRate.parser.CurrencyRateParser;
import org.springframework.stereotype.Service;

@Service
public class CurrencyRateService {
    public CurrencyRate fetchCurrencyRate(String sourceCurrency, String targetCurrency){
        CurrencyRateParser parser = new CurrencyRateParser();
        return parser.fetchCurrencyRateFromUrlJson(parser.getCurrencyRateFromUrl(sourceCurrency), sourceCurrency, targetCurrency);
    }
}
