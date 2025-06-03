package com.tfg.tfg_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.tfg_backend.dto.SeasonDTO;

@Service
public class SeasonService {
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SeasonService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<SeasonDTO> getSeasonByLeagueTeam(String league, String team) throws JsonMappingException, JsonProcessingException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" 
                + team + "/teams/" 
                + league + "/seasons?lang=en&region=en";
    
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");
        List<SeasonDTO> seasons = new ArrayList<>();
    
        if (items.isArray()) {
            for (JsonNode item : items) {
                // Extrae la URL de la season
                String seasonRef = item.path("$ref").asText();
                if (!seasonRef.isEmpty()) {
                    // Llama a ESPN para obtener datos de la temporada
                    String seasonJson = restTemplate.getForObject(seasonRef, String.class);
                    // Mapea ese JSON a un SeasonDTO
                    SeasonDTO season = objectMapper.readValue(seasonJson, SeasonDTO.class);
                    season.setAthletesRef("http://localhost:8080/api/teams/athletes/" + team + "?season=" + season.getYear());
                    seasons.add(season);
                }
            }
        }
        return seasons;
    }
}
