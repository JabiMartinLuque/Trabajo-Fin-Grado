package com.tfg.tfg_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tfg.tfg_backend.dto.TransactionDTO;
import com.tfg.tfg_backend.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")  // Mapping base para todas las rutas de este controlador
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/league/{league}")
    public ResponseEntity<List<TransactionDTO>> getSigningByLeagueAndSeason( //http://localhost:8080/api/transactions/league/esp.1
        @PathVariable("league") String league, 
        @RequestParam(value = "season", required = false) String season) throws JsonMappingException, JsonProcessingException { //http://localhost:8080/api/transactions/league/esp.1?season=2023
        List<TransactionDTO> response = transactionService.getSigningByLeagueAndSeason(league, season);
        return ResponseEntity.ok(response);
    }
    
}
