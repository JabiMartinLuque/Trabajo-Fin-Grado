package com.tfg.tfg_backend.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tfg.tfg_backend.dto.SeasonDTO;
import com.tfg.tfg_backend.service.SeasonService;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {
    
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("league/{league}/team/{team}")
    public ResponseEntity<List<SeasonDTO>> getSeasonByLeagueTeam(
        @PathVariable("league") String league,
        @PathVariable("team") String team ) throws JsonMappingException, JsonProcessingException { //http://localhost:8080/api/seasons/league/esp.1/team/86
        List<SeasonDTO> response = seasonService.getSeasonByLeagueTeam(team, league);
        return ResponseEntity.ok(response);
    }

}
