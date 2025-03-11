package com.tfg.tfg_backend.controller;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.tfg_backend.dto.TeamDTO;
import com.tfg.tfg_backend.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{team}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable("team") String team) throws IOException { //http://localhost:8080/api/teams/86
        TeamDTO response = teamService.getTeamById(team);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/league/{league}")
    public ResponseEntity<List<TeamDTO>> getTeamsByLeague(@PathVariable("league") String league, 
        @RequestParam(value = "season", required = false) String season) throws IOException { //http://localhost:8080/api/teams/league/esp.1?season=2024
        List<TeamDTO> response = teamService.getTeamsByLeague(league, season);
        return ResponseEntity.ok(response);
    }

    //No fufa
    @GetMapping("/details")
    public ResponseEntity<TeamDTO> getTeamDetails(@RequestParam("team") String teamRef) { //http://localhost:8080/api/teams/details?team=86
        TeamDTO response = teamService.getTeamDetails(teamRef);
        return ResponseEntity.ok(response);
    }  
    
}
