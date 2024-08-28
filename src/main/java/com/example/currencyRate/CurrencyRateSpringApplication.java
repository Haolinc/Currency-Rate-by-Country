package com.example.currencyRate;

import com.example.currencyRate.constant.AppConstant;
import com.example.currencyRate.data.CountryByCurrency;
import com.example.currencyRate.data.MapProcessing;
import com.example.currencyRate.parser.FileJsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CurrencyRateSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateSpringApplication.class, args);
		FileJsonReader fileJsonReader = new FileJsonReader();
		List<CountryByCurrency> countries = fileJsonReader.readFromArrayJson(AppConstant.APP_PATH + "/json/country-by-currency-code.json", CountryByCurrency.class);
		System.out.println("initializing maps---------------------------");
		for(CountryByCurrency countryByCurrency : countries){
			MapProcessing.countryCurrencySet.add(countryByCurrency.getCurrency());
			MapProcessing.countryNameToCurrencyMap.put(countryByCurrency.getEn(), countryByCurrency.getCurrency().toLowerCase());
		}
		System.out.println("initializing maps finish---------------------");
	}
}
