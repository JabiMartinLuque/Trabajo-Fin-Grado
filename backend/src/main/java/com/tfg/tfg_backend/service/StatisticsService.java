package com.tfg.tfg_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.dto.StatisticsDTO;

@Service
public class StatisticsService {
    
    private final RestTemplate restTemplate;

    public StatisticsService() {
        this.restTemplate = new RestTemplate();
    }

    public StatisticsDTO getStatistics(String season, String team, String league) {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + "/seasons/" + season + "/types/1/teams/" + team + "/statistics?lang=en&region=en";
        return restTemplate.getForObject(url, StatisticsDTO.class);
    }
}
