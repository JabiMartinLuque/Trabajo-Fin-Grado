package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StandingDTO {

    private TeamDTO team;
    private List<RecordDTO> records;

    // Getters y setters
    public TeamDTO getTeam() {
        return team;
    }
    public void setTeam(TeamDTO team) {
        this.team = team;
    }
    public List<RecordDTO> getRecords() {
        return records;
    }
    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }
}
