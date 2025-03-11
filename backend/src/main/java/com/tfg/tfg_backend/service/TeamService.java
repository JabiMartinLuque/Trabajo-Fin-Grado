package com.tfg.tfg_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.tfg_backend.dto.SeasonDTO;
import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.dto.TeamDTO.GroupDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TeamService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public TeamDTO getTeamDetails(String teamRef) {
        // Llama a la URL que viene en el campo  para obtener los detalles completos del equipo
        return restTemplate.getForObject(teamRef, TeamDTO.class);
    }

    public TeamDTO enrichTeamForStanding(TeamDTO team) {
        // Si el objeto TeamDTO tiene un , se hace la llamada para obtener más detalles
        if (team.getRef() != null) {
            return restTemplate.getForObject(team.getRef(), TeamDTO.class);
        }
        return team;
    }

    public TeamDTO getTeamById(String id) throws IOException {
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/teams/" + id + "?lang=es&region=es";
        TeamDTO team = restTemplate.getForObject(url, TeamDTO.class);
        String league = team.getLeague();
        String currentSeason = team.getCurrentSeason();
        if (team != null) {
            // Reemplaza la referencia externa de athletes por la URL interna de tu backend
            team.setAthletesRef("http://localhost:8080/api/teams/athletes/" + id);
            team.setStatisticsRef("http://localhost:8080/api/statistics/team/" + id + "?team=" + league + "&season=" + currentSeason);
        }

        String jsonResponse = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode groupsNode = root.path("groups");
        String groupsRef = groupsNode.path("$ref").asText();
        if (!groupsRef.isEmpty()) {
            String groupJson = restTemplate.getForObject(groupsRef, String.class);
            GroupDTO group = objectMapper.readValue(groupJson, GroupDTO.class);

            group.setStandingRef("http://localhost:8080/api/standings?league=esp.1");
            team.setGroup(group);
            
            JsonNode groupRoot = objectMapper.readTree(groupJson);
            String seasonRef = groupRoot.path("season").path("$ref").asText();
            if (!seasonRef.isEmpty()) {
                String seasonJson = restTemplate.getForObject(seasonRef, String.class);
                SeasonDTO season = objectMapper.readValue(seasonJson, SeasonDTO.class);
                season.setAthletesRef("http://localhost:8080/api/athletes/team/" + id);
                group.setSeason(season);
            }

            //group.setStandingsRef("http://localhost:8080/api/standings?league=esp.1");
            team.setGroup(group);
        }
        
        return team;
    }

    public List<TeamDTO> getTeamsByLeague(String league, String season) throws IOException {
        if (season == null || season.trim().isEmpty()) {
            season = "2024";
        }
        // Endpoint externo que devuelve la respuesta paginada con un array "items" de $ref
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/" 
                + league + "/seasons/" + season + "/teams?lang=es&region=es";
        
        // Obtenemos la respuesta completa en forma de String
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");
        List<TeamDTO> teams = new ArrayList<>();
        
        if (items.isArray()) {
            for (JsonNode item : items) {
                // Extraemos el $ref de cada item
                String ref = item.path("$ref").asText();
                if (ref != null && !ref.isEmpty()) {
                    // Obtenemos el objeto TeamDTO completo haciendo una llamada al $ref
                    TeamDTO team = restTemplate.getForObject(ref, TeamDTO.class);
                    if (team != null) {
                        // Enriquecemos el objeto reemplazando las URL externas por las internas.
                        // Por ejemplo, para el equipo:
                        String internalTeamUrl = "http://localhost:8080/api/teams/" + team.getId();
                        team.setRef(internalTeamUrl);
                        // Para los atletas:
                        team.setAthletesRef("http://localhost:8080/api/athletes/team/" + team.getId());
                        // Puedes enriquecer otros campos si lo deseas...
                    }
                    teams.add(team);
                }
            }
        }
        return teams;
    }

    
}

