package com.tfg.tfg_backend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora otros campos que no estén definidos
public class ScoreboardDTO {

    @JsonProperty("events") // Mapea la propiedad "events" del JSON
    private List<EventDTO> events;

    // Getters y setters
    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
