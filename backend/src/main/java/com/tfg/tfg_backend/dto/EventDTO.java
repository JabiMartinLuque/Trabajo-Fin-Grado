package com.tfg.tfg_backend.dto;

import java.util.List;

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitionDTO {

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
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VenueDTO {

        @JsonProperty("displayName")
        private String displayName;

        // Getters y setters

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitorDTO {

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
}

