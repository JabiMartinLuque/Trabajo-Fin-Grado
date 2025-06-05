package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonDTO {

    @JsonProperty("$ref")
    private String ref;
    
    private int year;
    private String startDate;
    private String endDate;
    private String displayName;
    private String shortDisplayName;
    private String abbreviation;
    
    private String athletesRef;

    // Getters y setters
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getShortDisplayName() {
        return shortDisplayName;
    }
    public void setShortDisplayName(String shortDisplayName) {
        this.shortDisplayName = shortDisplayName;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getAthletesRef() {
        return athletesRef;
    }
    public void setAthletesRef(String athletesRef) {
        this.athletesRef = athletesRef;
    }

    @JsonProperty("athletes")
    public void handleAthletes(JsonNode node) {
        // Aquí ignoramos el contenido real y no fallará
    }
    
}
