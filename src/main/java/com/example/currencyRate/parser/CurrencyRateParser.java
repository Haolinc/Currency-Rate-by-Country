package com.example.currencyRate.parser;

import com.example.currencyRate.data.CurrencyRate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;

public class CurrencyRateParser {
    // Fetch json and return as inputStream from rate url
    // Source link: https://github.com/fawazahmed0/exchange-api/tree/main?tab=readme-ov-file
    public InputStream getCurrencyRateFromUrl(String sourceCurrency) {
        try {
            // URL of the resource on jsDelivr
            String url = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/" + sourceCurrency + ".json";

            // Create a URL object
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();

            // Get the response code
            int responseCode = con.getResponseCode();

            // If Url is invalid
            if (responseCode >= 400 && responseCode <= 500){
                //Try backup url
                url = "https://latest.currency-api.pages.dev/v1/currencies/" + sourceCurrency + ".json";
                con = (HttpURLConnection) URI.create(url).toURL().openConnection();
            }
            return con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read inputStream json to get currency exchange rate and date
    public CurrencyRate fetchCurrencyRateFromUrlJson(InputStream inputStream, String sourceCurrency, String targetCurrency) {
        try{
            // Read the inputStream json
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine, date = "";
            BigDecimal rate = new BigDecimal(0);
            while ((inputLine = in.readLine()) != null) {
                String jsonKey = extractKeyStringFromJson(inputLine);
                // Grab Date
                if (jsonKey.equals("date")){
                    date = extractValueStringFromJson(inputLine);
                    if (targetCurrency.equals(sourceCurrency)) {
                        rate = new BigDecimal(1);
                        break;
                    }
                }
                // Grab the value for currency rate
                else if (jsonKey.equals(targetCurrency)){
                    rate = new BigDecimal(extractValueStringFromJson(inputLine));
                    break;
                }
            }
            in.close();
            return new CurrencyRate(sourceCurrency, targetCurrency, rate, date);
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String extractValueStringFromJson(String inputLine){
        String[] splitArr = inputLine.split(":");
        return splitArr[1].replaceAll("[^a-zA-Z0-9\\-.]", "");
    }
    private String extractKeyStringFromJson(String inputLine){
        String[] splitArr = inputLine.split(":");
        return splitArr[0].replaceAll("[^a-zA-Z\\-]", "");
    }
}
