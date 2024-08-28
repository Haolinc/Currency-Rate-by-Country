package com.example.currencyRate.legacyCode;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonWriter {
    public void writeIntoJson(String newPath, List<Country> countryList){
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        List<CountryWithCurrency> countryWithCurrencies = readFromArrayJson("./src/legacyJson/country-by-currency-code.json", CountryWithCurrency.class);
        List<NewCountry> newCountry = new LinkedList<>();

        System.out.println(countryWithCurrencies.size());
        System.out.println(countryList.size());
        System.out.println(countryWithCurrencies);

        Map<String, Country> countryMap = new HashMap<>();
        for(Country country : countryList){
            countryMap.put(country.en, country);
        }
        List<String> notCountedCountry = new ArrayList<>();
        for(CountryWithCurrency countryWithCurrency: countryWithCurrencies){
            String currentCountryName = countryWithCurrency.getCountry();
            if (countryMap.containsKey(currentCountryName)){
                Country currentCountry = countryMap.get(currentCountryName);
                newCountry.add(
                        new NewCountry(currentCountry.getZh(), currentCountry.getEn(), currentCountry.getAlpha3(), countryWithCurrency.currency_code)
                );
            }
            else {
                notCountedCountry.add(currentCountryName);
            }
        }
        notCountedCountry.forEach(System.out::println);

//        try {
//            // Write object to JSON file
//            objectMapper.writeValue(new File(newPath), newCountry);
//            System.out.println("JSON file created: user.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private <T> List<T> readFromArrayJson(String path, Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonContent = Files.readString(Paths.get(path));
            return objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
