package com.tfg.tfg_backend.controller;

import org.apache.catalina.connector.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<TeamDTO> getTeamById(@RequestParam("team") String team) { //http://localhost:8080/api/teams?team=86
        TeamDTO response = teamService.getTeamById(team);
        return ResponseEntity.ok(response);
    }

    //No fufa
    @GetMapping("/details")
    public ResponseEntity<TeamDTO> getTeamDetails(@RequestParam("team") String teamRef) { //http://localhost:8080/api/teams/details?team=86
        TeamDTO response = teamService.getTeamDetails(teamRef);
        return ResponseEntity.ok(response);
    }

    /*
     *  // Endpoint para obtener los detalles de un equipo dado su id, por ejemplo: GET /api/teams/83
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable("teamId") String teamId) {
        TeamDTO team = teamService.getTeamById(teamId);
        return ResponseEntity.ok(team);
    }
     */

     
    
}
