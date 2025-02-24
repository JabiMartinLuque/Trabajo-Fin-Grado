package com.tfg.tfg_backend.service;

import com.tfg.tfg_backend.dto.StandingDTO;
import com.tfg.tfg_backend.dto.TeamDTO;

import com.tfg.tfg_backend.service.TeamService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

@Service
public class StandingService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final TeamService teamService;

    public StandingService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.teamService = new TeamService();
    }

    /*
    public List<StandingDTO> getStandingsByLeague(String league) throws IOException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + "/seasons/2024/types/1/groups/1/standings/0?lang=en&region=es";
        // Obtener la respuesta completa en forma de String
        String jsonResponse = restTemplate.getForObject(url, String.class);
        // Leer la respuesta como un árbol JSON
        JsonNode root = objectMapper.readTree(jsonResponse);
        // Extraer el nodo "standings"
        JsonNode standingsNode = root.path("standings");
        // Mapear el array a una lista de StandingDTO
        List<StandingDTO> standings = objectMapper.readerForListOf(StandingDTO.class).readValue(standingsNode);
        return standings;
    }
    */
    public List<StandingDTO> getStandingsByLeague(String league) throws IOException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" 
                     + league 
                     + "/seasons/2024/types/1/groups/1/standings/0?lang=en&region=es";
        // Obtener la respuesta completa en forma de String
        String jsonResponse = restTemplate.getForObject(url, String.class);
        // Leer la respuesta como un árbol JSON
        JsonNode root = objectMapper.readTree(jsonResponse);
        // Extraer el nodo "standings"
        JsonNode standingsNode = root.path("standings");
        // Mapear el array a una lista de StandingDTO
        List<StandingDTO> standings = objectMapper.readerForListOf(StandingDTO.class).readValue(standingsNode);

        // Enriquecer la información del equipo para cada StandingDTO
        for (StandingDTO standing : standings) {
            if (standing.getTeam() != null && standing.getTeam().getRef() != null) {
                TeamDTO enrichedTeam = teamService.enrichTeam(standing.getTeam());
                standing.setTeam(enrichedTeam);
            }
        }

        return standings;
    }
}

