package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// No usamos @JsonDeserialize aquí para evitar la recursión
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    // Capturamos el $ref, si viene
    @JsonProperty("$ref")
    private String ref;

    // Otros campos que puedan venir en la respuesta expandida
    @JsonProperty("id")
    private String id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("abbreviation")
    private String abbreviation;

    // Campo para almacenar directamente el logo (la URL)
    private String logo;

    // Getters y setters

    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    // Este setter se usará para mapear el array "logos" y extraer el "href" del primer elemento
    @JsonProperty("logos")
    public void setLogosFromArray(List<Object> logos) {
        // Aquí usamos Object en lugar de crear un DTO si no necesitas más datos
        if (logos != null && !logos.isEmpty()) {
            // Convertir el primer elemento a un mapa para extraer el campo "href"
            Object firstElement = logos.get(0);
            // Suponiendo que el primer elemento es un LinkedHashMap
            if (firstElement instanceof java.util.Map) {
                java.util.Map<?, ?> logoMap = (java.util.Map<?, ?>) firstElement;
                Object href = logoMap.get("href");
                if (href != null) {
                    this.logo = href.toString();
                }
            }
        }
    }
}
