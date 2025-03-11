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
@RequestMapping("/api/teams/athletes")
public class AthleteController {

    private final AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/{team}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByTeam(@PathVariable("team") String teamId) throws IOException { //http://localhost:8080/api/teams/athletes/86
        List<AthleteDTO> athletes = athleteService.getAthletesByTeam(teamId);
        return ResponseEntity.ok(athletes);
    }
}
