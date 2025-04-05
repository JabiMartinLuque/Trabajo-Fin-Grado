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

        @JsonProperty("status")
        private StatusDTO status;

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

        public StatusDTO getStatus() {
            return status;
        }

        public void setStatus(StatusDTO status) {
            this.status = status;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatusDTO {

        @JsonProperty("clock")
        private String clock;

        @JsonProperty("displayClock")
        private String displayClock;

        @JsonProperty("type")
        private TypeDTO type;

        // Getters y setters

        public TypeDTO getType() {
            return type;
        }

        public void setType(TypeDTO type) {
            this.type = type;
        }

        public String getClock() {
            return clock;
        }

        public void setClock(String clock) {
            this.clock = clock;
        }

        public String getDisplayClock() {
            return displayClock;
        }

        public void setDisplayClock(String displayClock) {
            this.displayClock = displayClock;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeDTO {

        @JsonProperty("name")
        private String name;

        @JsonProperty("state")
        private String state;

        @JsonProperty("completed")
        private boolean completed;

        // Getters y setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
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

    }
}

