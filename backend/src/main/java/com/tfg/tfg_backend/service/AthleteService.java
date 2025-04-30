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

    public List<String> getAthletesByLeague(String league, String season) throws IOException {
        if (season == null || season.trim().isEmpty()) {
            season = "2024";
        }
        List<String> result = new ArrayList<>();
        // URL base para obtener atletas de la liga
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/" 
                + league + "/seasons/" + season + "/athletes?lang=es&region=es";
        
        int currentPage = 1;
        int totalPages = 1;
        boolean morePages = true;
    
        while (morePages) {
            String paginatedUrl = url + "&page=" + currentPage;
            String jsonResponse = restTemplate.getForObject(paginatedUrl, String.class);
            System.out.println(jsonResponse);
            JsonNode root = objectMapper.readTree(jsonResponse);
            totalPages = root.path("pageCount").asInt(1);

    
            JsonNode itemsNode = root.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    // Se extrae el id directamente del campo "id"
                    String ref = itemNode.path("$ref").asText();
                if (ref != null && !ref.isEmpty()) {
                    // Opcional: haz otra petición o parsea el ID desde la URL
                    // Suponiendo que al final del $ref va un número con ?lang y &region:
                    // https://sports.core.api.espn.com/v2/sports/soccer/athletes/1234?lang=en&region=es
                    // Extrae 1234
                    int idx = ref.lastIndexOf("/");
                    String rawId = idx != -1 ? ref.substring(idx + 1) : "";
                    if (rawId.contains("?")) {
                        rawId = rawId.substring(0, rawId.indexOf("?"));
                    }
                    String internalUrl = "http://localhost:8080/api/athletes/" + rawId;
                    result.add(internalUrl);
                }
                }
            }
            currentPage++;
            morePages = currentPage <= totalPages;
        }
        
        return result;
    }
    
    

    public AthleteDTO getAthleteById(String athleteId) throws IOException {
        // URL base para obtener detalles de un atleta (ajusta según la documentación de la API)
        String url = "https://sports.core.api.espn.com/v2/sports/soccer/athletes/" + athleteId + "?lang=es&region=es";
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
    public List<AthleteDTO> getAthletesByTeam(String teamId, String leagueId) throws IOException {
        List<AthleteDTO> result = new ArrayList<>();
        // URL base para obtener atletas del equipo (ajusta según la documentación de la API)
        String baseUrl = "https://sports.core.api.espn.com/v2/sports/soccer/leagues/" + leagueId + "/seasons/2024/teams/"
                         + teamId + "/athletes?lang=es&region=es";

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

