package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("date")
    private String date;  // Puedes usar String o convertirlo a LocalDateTime

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortName")
    private String shortName;

    // Podrías incluir solo la primera competencia (siempre hay una) o un resumen de ella:
    @JsonProperty("competitions")
    private CompetitionDTO[] competitions; // Por simplicidad, lo manejamos como un array

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public CompetitionDTO[] getCompetitions() {
        return competitions;
    }

    public void setCompetitions(CompetitionDTO[] competitions) {
        this.competitions = competitions;
    }
}