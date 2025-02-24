package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.dto.StandingDTO;
import com.tfg.tfg_backend.service.StandingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import java.io.IOException;

@RestController
@RequestMapping("/api/standings")
public class StandingController {
    
    private final StandingService standingService;

    public StandingController(StandingService standingService) {
        this.standingService = standingService;
    }

    @GetMapping
    public ResponseEntity<List<StandingDTO>> getStandings(@RequestParam("league") String league) { //http://localhost:8080/api/standings?league=esp.1
        try {
            List<StandingDTO> response = standingService.getStandingsByLeague(league);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
