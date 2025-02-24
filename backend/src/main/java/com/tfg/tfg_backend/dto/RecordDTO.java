package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordDTO {

    private String id;
    private String name;
    private String abbreviation;
    private String displayName;
    private String shortDisplayName;
    private String summary;
    private String displayValue;

    @JsonProperty("stats")
    private List<StatDTO> stats;

    // Getters y setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getDisplayValue() {
        return displayValue;
    }
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    public List<StatDTO> getStats() {
        return stats;
    }
    public void setStats(List<StatDTO> stats) {
        this.stats = stats;
    }
}
