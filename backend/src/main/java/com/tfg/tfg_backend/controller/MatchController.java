package com.tfg.tfg_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tfg.tfg_backend.dto.ScoreboardDTO;
import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.dto.TeamEventDTO;
import com.tfg.tfg_backend.service.MatchService;

import io.jsonwebtoken.io.IOException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")  // Mapping base para todas las rutas de este controlador
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/english")
    public ResponseEntity<ScoreboardDTO> getEnglishMatches() { //http://localhost:8080/api/matches/english
        ScoreboardDTO response = matchService.getEnglishMatches();
        return ResponseEntity.ok(response);
    }

    //Aqui se puede filtar por fechas
    @GetMapping("/league/{league}")
    public ResponseEntity<ScoreboardDTO> getMatchesByLeague(
        @PathVariable("league") String league, 
        @RequestParam(value = "startDate", required = false) String startDate,
        @RequestParam(value = "endDate", required = false) String endDate) { //http://localhost:8080/api/matches/league/esp.1
        ScoreboardDTO response = matchService.getMatchesByLeague(league, startDate, endDate);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint para obtener los partidos de un equipo en la temporada indicada, 
     * de todas sus competiciones o filtrado por una liga específica si se proporciona.
     * Ejemplo: GET /api/matches/86?&season=2024
     *          GET /api/matches/86?&season=2024&league=esp.1
     *
     * @param teamId Identificador del equipo.
     * @param season Temporada (opcional, por defecto "2024").
     * @param league Liga o competición (opcional).
     * @return Lista unificada de TeamEventDTO.
     * @throws IOException
          * @throws JsonProcessingException 
          * @throws JsonMappingException 
          */
         @GetMapping("/team/{team}")
         public ResponseEntity<List<TeamEventDTO>> getMatchesByTeamAndSeason( //http://localhost:8080/api/matches/team/86?season=2024
                 @PathVariable("team") String teamId,
                 @RequestParam(value = "season", required = false) String season,
                 @RequestParam(value = "league", required = false) String league) throws IOException, JsonMappingException, JsonProcessingException {
        List<TeamEventDTO> events = matchService.getMatchesByTeamAcrossLeagues(teamId, season, league);
        return ResponseEntity.ok(events);
    }


}
