package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.dto.AthleteDTO;
import com.tfg.tfg_backend.service.AthleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@CrossOrigin(origins = "*")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/league/{league}")
    public ResponseEntity<List<String>> getAthletesByLeague(
        @PathVariable("league") String leagueId,
        @RequestParam(value = "season", required = false) String season) throws IOException { //http://localhost:8080/api/athletes/league/esp.1
            List<String> athletes = athleteService.getAthletesByLeague(leagueId, season);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByTeam(@PathVariable("team") String teamId) throws IOException { //http://localhost:8080/api/athletes/team/86
        List<AthleteDTO> athletes = athleteService.getAthletesByTeam(teamId);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/{athlete}")
    public ResponseEntity<AthleteDTO> getAthleteById(@PathVariable("athlete") String athleteId) throws IOException { //http://localhost:8080/api/athletes/252107
        AthleteDTO athlete = athleteService.getAthleteById(athleteId);
        return ResponseEntity.ok(athlete);
    }
}
