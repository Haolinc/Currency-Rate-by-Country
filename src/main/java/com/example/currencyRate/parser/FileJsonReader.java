package com.example.currencyRate.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileJsonReader {
    // Read Json file that is solely array into List
    public <T> List<T> readFromArrayJson(String path, Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonContent = Files.readString(Paths.get(path));
            return objectMapper.readValue(jsonContent, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Read Json file into List
    public <T> T readFromJson(String path, Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonContent = Files.readString(Paths.get(path));
            return objectMapper.readValue(jsonContent, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
