package com.csc340.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/numbers")
public class NumbersApiController {

    private static final String BASE_URL = "http://numbersapi.com/";

    @GetMapping("/{number}")
    public String getNumberFact(@PathVariable String number) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + number + "?json";
        String jsonResponse = restTemplate.getForObject(url, String.class);

        // Parse JSON response
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            return jsonNode.get("text").asText();  // Extract only the fact text
        } catch (Exception e) {
            return "Error parsing JSON";
        }
    }
}