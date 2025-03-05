package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompetitorDTO {

    @JsonProperty("homeAway")
    private String homeAway;

    @JsonProperty("winner")
    private boolean winner;

    @JsonProperty("score")
    private String score;

    @JsonProperty("team")
    private TeamDTO team;

    // Getters y setters

    public String getHomeAway() {
        return homeAway;
    }

    public void setHomeAway(String homeAway) {
        this.homeAway = homeAway;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @JsonProperty("score")
    public void setScoreFromObject(Object scoreObj) {
        // Si scoreObj es un mapa, extraer displayValue
        if (scoreObj instanceof java.util.Map) {
            java.util.Map<?,?> map = (java.util.Map<?,?>) scoreObj;
            Object displayVal = map.get("displayValue");
            if (displayVal != null) {
                this.scoreAsString = displayVal.toString();
            }
        } else if (scoreObj != null) {
            this.scoreAsString = scoreObj.toString();
        }
    }

    // Campo para almacenar la representación en String
    private String scoreAsString;

    public String getScoreAsString() {
        return scoreAsString;
    }


}
