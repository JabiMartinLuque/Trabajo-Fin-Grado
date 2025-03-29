package com.tfg.tfg_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.tfg_backend.service.TopPerformersService;
    
import com.tfg.tfg_backend.dto.TopPerformersDTO;

@RestController
@RequestMapping("/api/topPerformers/team")
@CrossOrigin(origins = "*")
public class TopPerformersController {
            
    private final TopPerformersService topPerformersService;
    
    public TopPerformersController(TopPerformersService topPerformersService) {
        this.topPerformersService = topPerformersService;
    }
    
    @GetMapping("/{team}")
    public TopPerformersDTO getTopPerformers(
        @PathVariable("team") String team, 
        @RequestParam("season") String season, 
        @RequestParam("league") String league) { //http://localhost:8080/api/topPerformers/teams/86?season=2024&league=esp.1
        return topPerformersService.getTopPerformers(season, league, team);
    }
}
