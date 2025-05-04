package com.tfg.tfg_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfg.tfg_backend.dto.NewsDTO;
import com.tfg.tfg_backend.service.NewsService;

@Controller
@RequestMapping("/api/news")  
@CrossOrigin(origins = "*")
public class NewsController {
    private final NewsService newsService;
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @GetMapping("/league/{league}")
    public ResponseEntity<NewsDTO> getNewsByLeague(@PathVariable("league") String league) { //http://localhost:8080/api/news/league/esp.1
        NewsDTO response = newsService.getNewsByLeague(league); 
        return ResponseEntity.ok(response);
    }
}
