package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.dto.AthleteDTO;
import com.tfg.tfg_backend.service.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/league/{league}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByLeague(@PathVariable("league") String leagueId) throws IOException { //http://localhost:8080/api/athletes/league/esp.1
        List<AthleteDTO> athletes = athleteService.getAthletesByLeague(leagueId);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByTeam(@PathVariable("team") String teamId) throws IOException { //http://localhost:8080/api/athletes/team/86
        List<AthleteDTO> athletes = athleteService.getAthletesByTeam(teamId);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/{athlete}")
    public ResponseEntity<AthleteDTO> getAthleteById(@PathVariable("athlete") String athleteId) throws IOException { //http://localhost:8080/api/athletes/3253
        AthleteDTO athlete = athleteService.getAthleteById(athleteId);
        return ResponseEntity.ok(athlete);
    }
}
