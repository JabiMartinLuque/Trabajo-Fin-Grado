package com.tfg.tfg_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.tfg_backend.dto.EventDTO;
import com.tfg.tfg_backend.dto.ScoreboardDTO;
import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.dto.TeamEventDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.service.TeamService;

import io.jsonwebtoken.io.IOException;

@Service
public class MatchService {
    private final RestTemplate restTemplate;
    private final TeamService teamService;
    private final ObjectMapper objectMapper;

    public MatchService() {
        this.restTemplate = new RestTemplate();
        this.teamService = new TeamService();
        this.objectMapper = new ObjectMapper();
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

    /* 
    public ScoreboardDTO getMatchesByTeam(String teamId) {
    // Primero, obtenemos el objeto TeamDTO a partir del id del equipo
    TeamDTO team = teamService.getTeamById(teamId);
    
    // Extraemos la URL interna (eventsRef) desde el objeto de equipo
    String eventsRef = team.getEventsRef();
    
    // Usamos esa URL para obtener el ScoreboardDTO, que contendrá los partidos del equipo
    ScoreboardDTO response = restTemplate.getForObject(eventsRef, ScoreboardDTO.class);
    
    return response;
    }
    */
    
    public EventDTO getMatchesByLeagueSeasonTeam(String league, String team, String season) {
        if (season == null || season.trim().isEmpty()) {
            season = "2024";
        }
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/"
                + league + "/seasons/" + season + "/teams/" + team + "/events?lang=es&region=es";
        return restTemplate.getForObject(url, EventDTO.class);
    }

    /**
     * Obtiene los partidos (TeamEventDTO) de un equipo para la temporada indicada a partir de todas sus competiciones.
     * Si season es nula o vacía, se usará "2024" por defecto.
     * Si se especifica una liga (league no vacía), se usarán sólo los eventos de esa competición.
     *
     * @param teamId Identificador del equipo (por ejemplo, "83")
     * @param season Temporada (por defecto "2024")
     * @param league Liga o competición (opcional). Ejemplo: "esp.1"
     * @return Lista unificada de TeamEventDTO con los eventos
     * @throws IOException, JsonMappingException, JsonProcessingException
     */
    public List<TeamEventDTO> getMatchesByTeamAcrossLeagues(String teamId, String season, String league)
            throws IOException, JsonMappingException, JsonProcessingException {
        if (season == null || season.trim().isEmpty()) {
            season = "2024";
        }

        // 1. Determinar la lista de competiciones a usar.
        List<String> leagueIds = new ArrayList<>();
        if (league != null && !league.trim().isEmpty()) {
            leagueIds.add(league);
        } else {
            // Si no se especifica liga, obtener las ligas en las que participa el equipo.
            String leaguesUrl = "http://sports.core.api.espn.com/v2/sports/soccer/teams/" 
                    + teamId + "/leagues?lang=es&region=es";
            String leaguesResponse = restTemplate.getForObject(leaguesUrl, String.class);
            JsonNode leaguesRoot = objectMapper.readTree(leaguesResponse);
            JsonNode leaguesItems = leaguesRoot.path("items");
            if (leaguesItems.isArray()) {
                for (JsonNode leagueNode : leaguesItems) {
                    // Extraer el $ref y obtener el identificador de la liga.
                    String ref = leagueNode.path("$ref").asText();
                    if (ref != null && !ref.isEmpty()) {
                        String[] parts = ref.split("/");
                        for (int i = 0; i < parts.length; i++) {
                            if ("leagues".equals(parts[i]) && i + 1 < parts.length) {
                                String leagueId = parts[i + 1];
                                if (leagueId.contains("?")) {
                                    leagueId = leagueId.split("\\?")[0];
                                }
                                leagueIds.add(leagueId);
                                System.out.println("Liga encontrada: " + leagueId);
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 2. Para cada competición, obtener los eventos del equipo para la temporada indicada.
        List<TeamEventDTO> allEvents = new ArrayList<>();
        for (String leagueId : leagueIds) {
            String eventsUrl = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/"
                    + leagueId + "/seasons/" + season + "/teams/" + teamId + "/events?lang=es&region=es";
            
            int currentPage = 1;
            int totalPages = 1;
            boolean morePages = true;
            while (morePages) {
                String paginatedUrl = eventsUrl + "&page=" + currentPage;
                String eventsResponse = restTemplate.getForObject(paginatedUrl, String.class);
                JsonNode eventsRoot = objectMapper.readTree(eventsResponse);
                totalPages = eventsRoot.path("pageCount").asInt(1);
                JsonNode eventsItems = eventsRoot.path("items");
                if (eventsItems.isArray()) {
                    for (JsonNode eventNode : eventsItems) {
                        // Obtener el $ref del evento para extraer el detalle completo.
                        String eventRef = eventNode.path("$ref").asText();
                        if (eventRef != null && !eventRef.isEmpty()) {
                            TeamEventDTO detailedEvent = restTemplate.getForObject(eventRef, TeamEventDTO.class);
                            allEvents.add(detailedEvent);
                        }
                    }
                }
                currentPage++;
                morePages = currentPage <= totalPages;
            }
        }
        return allEvents;
    }
}
