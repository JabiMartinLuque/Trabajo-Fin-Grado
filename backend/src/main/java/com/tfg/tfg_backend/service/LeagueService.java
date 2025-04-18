package com.tfg.tfg_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.dto.LeagueDTO;

@Service
public class LeagueService {
    
    private final RestTemplate restTemplate;
    String[] LEAGUE_IDS = {"eng.1", "esp.1", "ger.1", "ita.1", "fra.1"};

    public LeagueService() {
        this.restTemplate = new RestTemplate();
    }

    public LeagueDTO getLeagueById(String leagueId) {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + leagueId + "?lang=es&region=es";
        return restTemplate.getForObject(url, LeagueDTO.class);
    }

    public List<LeagueDTO> getAllLeagues() {
        List<LeagueDTO> leagues = new ArrayList<>();
        for(String leagueId : LEAGUE_IDS) {
            LeagueDTO league = getLeagueById(leagueId);
            leagues.add(league);
        }
        return leagues;
    }
}
