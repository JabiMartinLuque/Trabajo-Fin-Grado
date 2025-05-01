package com.tfg.tfg_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.tfg_backend.dto.EventDTO;
import com.tfg.tfg_backend.dto.ScoreboardDTO;
import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.dto.TeamEventDTO;
import com.tfg.tfg_backend.dto.ScoreValueDTO;
import com.tfg.tfg_backend.dto.LineUpDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

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

    public ScoreboardDTO getMatchesByLeague(String league, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate today = LocalDate.now();
        
        if (startDate == null || startDate.trim().isEmpty()) {
            startDate = today.format(formatter);
        }
        if (endDate == null || endDate.trim().isEmpty()) {
            endDate = today.plusWeeks(1).format(formatter);
        }
        
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/" 
                + league + "/scoreboard?dates=" + startDate + "-" + endDate + "&lang=es&region=es";
        
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
                            allEvents.add(resolveReferences(detailedEvent));
                        }
                    }
                }
                currentPage++;
                morePages = currentPage <= totalPages;
            }
        }
        return allEvents;
    }

    public EventDTO getMatchById(String id) throws IOException, JsonMappingException, JsonProcessingException {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/esp.1/scoreboard/" + id + "?lang=es&region=es";
        return resolveReferences(restTemplate.getForObject(url, EventDTO.class));
    }

    public TeamEventDTO getAthleteMatches(String id) throws IOException, JsonMappingException, JsonProcessingException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/athletes/" + id + "/events?lang=es&region=es";
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");

        if (items.isArray() && items.size() > 0) {
            JsonNode firstItem = items.get(0);
            String eventRef = firstItem.path("$ref").asText();
            if (eventRef != null && !eventRef.isEmpty()) {
                System.out.println("Evento encontrado: " + eventRef);
                return resolveReferences(restTemplate.getForObject(eventRef, TeamEventDTO.class));
            }
        } else {
            System.out.println("No se encontraron eventos para el atleta con ID: " + id);
        }
        return null; // O lanzar una excepción si no se encuentra el evento
        
    }

    public TeamEventDTO getTeamEvent(String id) throws IOException, JsonMappingException, JsonProcessingException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/teams/" + id + "/events?lang=es&region=es";
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");

        if (items.isArray() && items.size() > 0) {
            JsonNode firstItem = items.get(0);
            String eventRef = firstItem.path("$ref").asText();
            if (eventRef != null && !eventRef.isEmpty()) {
                System.out.println("Evento encontrado: " + eventRef);
                return resolveReferences(restTemplate.getForObject(eventRef, TeamEventDTO.class));
            }
        } else {
            System.out.println("No se encontraron eventos para el equipo con ID: " + id);
        }
        return null; // O lanzar una excepción si no se encuentra el evento

    }

    private TeamEventDTO resolveReferences(TeamEventDTO event) throws IOException, JsonMappingException, JsonProcessingException {
        // Aquí puedes resolver las referencias de los equipos y atletas
        if (event.getCompetitions() != null) {
            for (TeamEventDTO.CompetitionDTO competition : event.getCompetitions()) {
                if (competition.getCompetitors() == null) continue;

                for (TeamEventDTO.CompetitorDTO competitor : competition.getCompetitors()) {
                    // Para SCORE
                    if (competitor.getScore() != null) {
                        
                        String scoreRef = competitor.getScore().getRef();
                        if (scoreRef != null && !scoreRef.isEmpty()) {
                            // Llamada a ESPN para obtener el objeto ScoreValueDTO completo
                            ScoreValueDTO score = restTemplate.getForObject(scoreRef, ScoreValueDTO.class);
                            competitor.setScore(score);
                        }
                    }
                    
                    if(competitor.getTeam() != null) {
                        String teamRef = competitor.getTeam().getRef();
                        if (teamRef != null && !teamRef.isEmpty()) {
                            // Llamada a ESPN para obtener el objeto TeamDTO completo
                            TeamDTO team = restTemplate.getForObject(teamRef, TeamDTO.class);
                            competitor.setTeam(team);
                        }
                    }

                    if(competitor.getLineup() != null) {
                        String lineupRef = competitor.getLineup().getRef();
                        System.out.println("Lineup Ref: " + lineupRef);
                        if (lineupRef != null && !lineupRef.isEmpty()) {
                            // Llamada a ESPN para obtener el objeto LineupDTO completo
                            LineUpDTO lineup = restTemplate.getForObject(lineupRef, LineUpDTO.class);
                            competitor.setLineup(lineup);
                        }
                    }
                }
            }
        }
        return event;
    }

    private EventDTO resolveReferences(EventDTO event) throws IOException, JsonMappingException, JsonProcessingException {
        // Aquí puedes resolver las referencias de los equipos y atletas
        for (EventDTO.CompetitionDTO competition : event.getCompetitions()) {
            if (competition.getCompetitors() != null) {
                for (EventDTO.CompetitorDTO competitor : competition.getCompetitors()) {
                    // Para SCORE
                    String league = getLeagueByTeamId(competitor.getTeam().getId());

                    String url = "http://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + 
                    "/events/" + event.getId() + "/competitions/" + event.getId() + "/competitors/" + competitor.getTeam().getId() + "/roster?lang=es&region=es";

                    LineUpDTO lineup = restTemplate.getForObject(url, LineUpDTO.class);
                    competitor.setLineUp(lineup);
                }
            }
        }
        
        return event;
    }

    private String getLeagueByTeamId(String teamId) throws JsonMappingException, JsonProcessingException {
        String url = "http://sports.core.api.espn.com/v2/sports/soccer/teams/" 
                + teamId + "/leagues?lang=es&region=es";
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        JsonNode items = root.path("items");
    
        if (items.isArray() && items.size() > 0) {
            for (JsonNode item : items) {
                String leagueRef = item.path("$ref").asText();
                if (leagueRef != null && !leagueRef.isEmpty()) {
                    String[] parts = leagueRef.split("/");
                    if (parts.length > 7) {
                        String leagueSegment = parts[7];
                        if (leagueSegment.contains("?")) {
                            leagueSegment = leagueSegment.split("\\?")[0];
                        }
                        // Busca específicamente "esp.1"
                        if ("esp.1".equals(leagueSegment) || "eng.1".equals(leagueSegment) || "fra.1".equals(leagueSegment) || "ger.1".equals(leagueSegment) || "ita.1".equals(leagueSegment)) {
                            return leagueSegment;
                        }
                    }
                }
            }
        }
        return null;
    }
}
