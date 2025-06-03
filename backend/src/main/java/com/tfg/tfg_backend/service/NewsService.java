package com.tfg.tfg_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tfg.tfg_backend.dto.NewsDTO;

@Service
public class NewsService {
    private final RestTemplate restTemplate;

    public NewsService() {
        this.restTemplate = new RestTemplate();
    }

    public NewsDTO getNewsByLeague(String league) {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/" + league + "/news?lang=en&region=en";
        return restTemplate.getForObject(url, NewsDTO.class);
    }

}
