package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.dto.ScoreboardDTO;
import com.tfg.tfg_backend.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<ScoreboardDTO> getEnglishMatches() {
        ScoreboardDTO response = matchService.getEnglishMatches();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ScoreboardDTO> getMatchesByLeague(@RequestParam("league") String league) {
        ScoreboardDTO response = matchService.getMatchesByLeague(league);
        return ResponseEntity.ok(response);
    }
}
