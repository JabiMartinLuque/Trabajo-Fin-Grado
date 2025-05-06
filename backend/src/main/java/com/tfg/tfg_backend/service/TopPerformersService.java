package com.tfg.tfg_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.dto.TopPerformersDTO;

@Service
public class TopPerformersService {
    private final RestTemplate restTemplate;

    public TopPerformersService() {
        this.restTemplate = new RestTemplate();
    }

    public TopPerformersDTO getTopPerformers(String season, String league, String team) {
        if (season == null ) {
            season = "2024"; // Valor por defecto
        }
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + "/seasons/" + season + "/types/1/teams/" + team + "/leaders?lang=es&region=es";
        return restTemplate.getForObject(url, TopPerformersDTO.class);
    }
}
