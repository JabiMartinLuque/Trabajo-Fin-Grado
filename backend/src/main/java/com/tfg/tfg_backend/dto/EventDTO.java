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

        @JsonProperty("details")
        private List<DetailDTO> details; // Añadido para los detalles del evento

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

        public List<DetailDTO> getDetails() {
            return details;
        }

        public void setDetails(List<DetailDTO> details) {
            this.details = details;
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

        private String fullName;

        public String getFullName() {
            return fullName;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
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

        private LineUpDTO lineUp; // Añadido para la alineación

        private List<StatisticDTO> statistics; // Añadido para las estadísticas

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

        public LineUpDTO getLineUp() {
            return lineUp;
        }

        public void setLineUp(LineUpDTO lineUp) {
            this.lineUp = lineUp;
        }

        public List<StatisticDTO> getStatistics() {
            return statistics;
        }

        public void setStatistics(List<StatisticDTO> statistics) {
            this.statistics = statistics;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailDTO {
        private TypeDetailDTO type;
        private ClockDTO clock;
        private TeamIDDTO team;
        private String scoreValue;
        private Boolean scoringPlay;
        private Boolean redCard;
        private Boolean yellowCard;
        private Boolean penaltyKick;
        private Boolean ownGoal;
        private Boolean shootout;
        private List<AthleteInvolvedDTO> athletesInvolved;

        public TypeDetailDTO getType() {
            return type;
        }
        public void setType(TypeDetailDTO type) {
            this.type = type;
        }
        public ClockDTO getClock() {
            return clock;
        }
        public void setClock(ClockDTO clock) {
            this.clock = clock;
        }
        public TeamIDDTO getTeam() {
            return team;
        }
        public void setTeam(TeamIDDTO team) {
            this.team = team;
        }
        public String getScoreValue() {
            return scoreValue;
        }
        public void setScoreValue(String scoreValue) {
            this.scoreValue = scoreValue;
        }
        public Boolean getScoringPlay() {
            return scoringPlay;
        }
        public void setScoringPlay(Boolean scoringPlay) {
            this.scoringPlay = scoringPlay;
        }
        public Boolean getRedCard() {
            return redCard;
        }
        public void setRedCard(Boolean redCard) {
            this.redCard = redCard;
        }
        public Boolean getYellowCard() {
            return yellowCard;
        }
        public void setYellowCard(Boolean yellowCard) {
            this.yellowCard = yellowCard;
        }
        public Boolean getPenaltyKick() {
            return penaltyKick;
        }
        public void setPenaltyKick(Boolean penaltyKick) {
            this.penaltyKick = penaltyKick;
        }
        public Boolean getOwnGoal() {
            return ownGoal;
        }
        public void setOwnGoal(Boolean ownGoal) {
            this.ownGoal = ownGoal;
        }
        public Boolean getShootout() {
            return shootout;
        }
        public void setShootout(Boolean shootout) {
            this.shootout = shootout;
        }
        public List<AthleteInvolvedDTO> getAthletesInvolved() {
            return athletesInvolved;
        }
        public void setAthletesInvolved(List<AthleteInvolvedDTO> athletesInvolved) {
            this.athletesInvolved = athletesInvolved;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeDetailDTO {
        private String id;
        private String text;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClockDTO {
        private String value;
        private String displayValue;

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getDisplayValue() {
            return displayValue;
        }
        public void setDisplayValue(String displayValue) {
            this.displayValue = displayValue;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TeamIDDTO {
        private String id;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AthleteInvolvedDTO {
        private String id;
        private String displayName;
        private String shortName;
        private String fullName;
        private String jersey;
        private TeamIDDTO team;

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
        public String getShortName() {
            return shortName;
        }
        public void setShortName(String shortName) {
            this.shortName = shortName;
        }
        public String getFullName() {
            return fullName;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
        public String getJersey() {
            return jersey;
        }
        public void setJersey(String jersey) {
            this.jersey = jersey;
        }
        public TeamIDDTO getTeam() {
            return team;
        }
        public void setTeam(TeamIDDTO team) {
            this.team = team;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatisticDTO {
        private String name;
        private String abbreviation;
        private String displayValue;

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
        public String getDisplayValue() {
            return displayValue;
        }
        public void setDisplayValue(String displayValue) {
            this.displayValue = displayValue;
        }
    }
        
}

