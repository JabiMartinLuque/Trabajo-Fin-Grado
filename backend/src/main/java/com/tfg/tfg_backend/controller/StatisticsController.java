package com.tfg.tfg_backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.tfg_backend.dto.StatisticsDTO;
import com.tfg.tfg_backend.service.StatisticsService;

@RestController
@RequestMapping("/api/statistics/team")
@CrossOrigin(origins = "*")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{team}")
    public ResponseEntity<StatisticsDTO> getStatistics(
        @PathVariable("team") String team, 
        @RequestParam("season") String season, 
        @RequestParam("league") String league) { //http://localhost:8080/api/statistics/team/86?season=2024&league=esp.1
        StatisticsDTO response = statisticsService.getStatistics(season, team, league);
        return ResponseEntity.ok(response);
    }

    
}
