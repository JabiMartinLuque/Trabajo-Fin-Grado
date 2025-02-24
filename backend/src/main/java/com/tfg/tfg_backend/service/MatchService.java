package com.tfg.tfg_backend.service;

import com.tfg.tfg_backend.dto.ScoreboardDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchService {
    private final RestTemplate restTemplate;

    public MatchService() {
        this.restTemplate = new RestTemplate();
    }

    public ScoreboardDTO getEnglishMatches() {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard?lang=es&region=es";
        // Jackson convertirá automáticamente el JSON en un objeto ScoreboardResponse
        return restTemplate.getForObject(url, ScoreboardDTO.class);
    }

    public ScoreboardDTO getMatchesByLeague(String league) {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/" + league + "/scoreboard?lang=es&region=es"; 
        return restTemplate.getForObject(url, ScoreboardDTO.class);
    }
}
