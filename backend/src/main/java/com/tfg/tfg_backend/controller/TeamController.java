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
    public ResponseEntity<TeamDTO> getTeamById(@RequestParam("team") String team) {
        TeamDTO response = teamService.getTeamById(team);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details")
    public ResponseEntity<TeamDTO> getTeamDetails(@RequestParam("teamRef") String teamRef) {
        TeamDTO response = teamService.getTeamDetails(teamRef);
        return ResponseEntity.ok(response);
    }
}
