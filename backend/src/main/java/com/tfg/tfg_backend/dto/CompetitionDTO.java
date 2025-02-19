package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitionDTO {

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("venue")
    private VenueDTO venue;

    @JsonProperty("competitors")
    private List<CompetitorDTO> competitors;

    // Getters y setters

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public VenueDTO getVenue() {
        return venue;
    }

    public void setVenue(VenueDTO venue) {
        this.venue = venue;
    }

    public List<CompetitorDTO> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<CompetitorDTO> competitors) {
        this.competitors = competitors;
    }
} 
