package com.tfg.tfg_backend.service;

import com.tfg.tfg_backend.dto.TeamDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamService {

    private final RestTemplate restTemplate;

    public TeamService() {
        this.restTemplate = new RestTemplate();
    }

    public TeamDTO getTeamDetails(String teamRef) {
        // Llama a la URL que viene en el campo $ref para obtener los detalles completos del equipo
        return restTemplate.getForObject(teamRef, TeamDTO.class);
    }

    public TeamDTO enrichTeam(TeamDTO team) {
        // Si el objeto TeamDTO tiene un $ref, se hace la llamada para obtener más detalles
        if (team.getRef() != null) {
            return restTemplate.getForObject(team.getRef(), TeamDTO.class);
        }
        return team;
    }

    public TeamDTO getTeamById(String id) {
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/teams/" + id + "?lang=es&region=es";
        TeamDTO team = restTemplate.getForObject(url, TeamDTO.class);
        if (team != null) {
            // Reemplaza la referencia externa de athletes por la URL interna de tu backend
            team.setAthletesRef("http://localhost:8080/api/teams/athletes?team=" + id);
        }
        return team;
    }
    
}

