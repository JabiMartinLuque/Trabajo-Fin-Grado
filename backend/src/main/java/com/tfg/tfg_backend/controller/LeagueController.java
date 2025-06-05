package com.tfg.tfg_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.tfg_backend.dto.LeagueDTO;
import com.tfg.tfg_backend.service.LeagueService;



@RestController
@RequestMapping("/api/leagues")  // Mapping base para todas las rutas de este controlador
@CrossOrigin(origins = "*")
public class LeagueController {
    
    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    // Endpoint para obtener información de una liga por su ID
    @GetMapping("/{leagueId}")
    public ResponseEntity<LeagueDTO> getLeagueById(@PathVariable("leagueId") String leagueId) {
        LeagueDTO league = leagueService.getLeagueById(leagueId);
        return ResponseEntity.ok(league);
    }

    @GetMapping("")
    public List<LeagueDTO> getAllLeagues() {
        return leagueService.getAllLeagues();
    }
}
