package com.tfg.tfg_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.dto.AthleteDTO;
import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.dto.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TransactionService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    //// filepath: c:\Users\jamar\Trabajo-Fin-Grado\backend\src\main\java\com\tfg\tfg_backend\service\TransactionService.java
public List<TransactionDTO> getSigningByLeagueAndSeason(String league, String season) throws JsonMappingException, JsonProcessingException {
    if (season == null || season.isEmpty()) {
        season = "2024";
    }
    String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + "/seasons/" + season + "/transactions?lang=en&region=en";
    String response = restTemplate.getForObject(url, String.class);
    JsonNode root = objectMapper.readTree(response);
    JsonNode items = root.path("items");
    List<TransactionDTO> transactions = new ArrayList<>();

    if (items.isArray()) {
        for (JsonNode item : items) {
            // Mapeo inicial del TransactionDTO
            TransactionDTO transaction = objectMapper.readValue(item.toString(), TransactionDTO.class);

            // Athlete
            String athleteRef = item.path("athlete").path("$ref").asText();
            if (athleteRef != null && !athleteRef.isEmpty()) {
                String athleteJson = restTemplate.getForObject(athleteRef, String.class);
                AthleteDTO athleteDetails = objectMapper.readValue(athleteJson, AthleteDTO.class);
                transaction.setAthlete(athleteDetails);
            }

            // Team From
            String fromRef = item.path("from").path("$ref").asText();
            if (fromRef != null && !fromRef.isEmpty()) {
                String fromJson = restTemplate.getForObject(fromRef, String.class);
                TeamDTO fromDetails = objectMapper.readValue(fromJson, TeamDTO.class);
                transaction.setTeamFrom(fromDetails);
            }

            // Team To
            String toRef = item.path("to").path("$ref").asText();
            if (toRef != null && !toRef.isEmpty()) {
                String toJson = restTemplate.getForObject(toRef, String.class);
                TeamDTO toDetails = objectMapper.readValue(toJson, TeamDTO.class);
                transaction.setTeamTo(toDetails);
            }

            transactions.add(transaction);
        }
    }

    return transactions;
}
    
}
