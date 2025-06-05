package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class TeamDeserializer extends JsonDeserializer<TeamDTO> {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public TeamDTO deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("$ref")) {
            String teamRef = node.get("$ref").asText();
            // Crea una nueva instancia de ObjectMapper sin el deserializador personalizado
            ObjectMapper mapper = new ObjectMapper();
            // Realiza la llamada REST sin activar este deserializador nuevamente
            String jsonTeam = restTemplate.getForObject(teamRef, String.class);
            return mapper.readValue(jsonTeam, TeamDTO.class);
        } else {
            // Si ya vienen los detalles, deserializa normalmente
            return jp.getCodec().treeToValue(node, TeamDTO.class);
        }
    }
}
