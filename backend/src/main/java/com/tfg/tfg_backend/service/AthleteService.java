package com.tfg.tfg_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfg.tfg_backend.dto.AthleteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AthleteService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AthleteService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<AthleteDTO> getAthletesByLeague(String league) throws IOException {
        List<AthleteDTO> result = new ArrayList<>();
        // URL base para obtener atletas de la liga (ajusta según la documentación de la API)
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/" + league + "/athletes?lang=en&region=es";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(jsonResponse);
        // Extraer el array de items (cada uno contiene el campo "$ref")
        JsonNode itemsNode = root.path("items");
        if (itemsNode.isArray()) {
            for (JsonNode itemNode : itemsNode) {
                JsonNode refNode = itemNode.path("$ref");
                if (!refNode.isMissingNode()) {
                    String athleteRef = refNode.asText();
                    // Obtiene detalles completos del atleta usando el $ref
                    AthleteDTO athleteDetail = restTemplate.getForObject(athleteRef, AthleteDTO.class);
                    result.add(athleteDetail);
                }
            }
        }
        return result;
    }

    public AthleteDTO getAthleteById(String athleteId) throws IOException {
        // URL base para obtener detalles de un atleta (ajusta según la documentación de la API)
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/athletes/" + athleteId + "?lang=en&region=es";
        return restTemplate.getForObject(url, AthleteDTO.class);
    }

    /**
     * Obtiene un listado unificado de atletas para el equipo indicado.
     * Recorre todas las páginas y acumula los detalles completos de cada atleta.
     *
     * @param teamId El identificador del equipo.
     * @return Lista de AthleteDTO con los detalles de cada atleta.
     * @throws IOException
     */
    public List<AthleteDTO> getAthletesByTeam(String teamId) throws IOException {
        List<AthleteDTO> result = new ArrayList<>();
        // URL base para obtener atletas del equipo (ajusta según la documentación de la API)
        String baseUrl = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/esp.1/seasons/2024/teams/"
                         + teamId + "/athletes?lang=en&region=es";

        int currentPage = 1;
        int totalPages = 1;
        boolean morePages = true;

        while (morePages) {
            // Agrega el parámetro de paginación a la URL
            String paginatedUrl = baseUrl + "&page=" + currentPage;
            String jsonResponse = restTemplate.getForObject(paginatedUrl, String.class);
            JsonNode root = objectMapper.readTree(jsonResponse);
            // Se asume que la respuesta incluye "pageCount"
            totalPages = root.path("pageCount").asInt(1);

            // Extraer el array de items (cada uno contiene el campo "$ref")
            JsonNode itemsNode = root.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode refNode = itemNode.path("$ref");
                    if (!refNode.isMissingNode()) {
                        String athleteRef = refNode.asText();
                        // Obtiene detalles completos del atleta usando el $ref
                        AthleteDTO athleteDetail = restTemplate.getForObject(athleteRef, AthleteDTO.class);
                        result.add(athleteDetail);
                    }
                }
            }
            currentPage++;
            morePages = currentPage <= totalPages;
        }
        return result;
    }
}

