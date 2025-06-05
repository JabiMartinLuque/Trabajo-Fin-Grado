package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamEventDTO {

    @JsonProperty("$ref")
    private String ref;
    private String id;
    private String date;
    private String name;
    private String shortName;
    private boolean timeValid;
    private List<CompetitionDTO> competitions;
    private List<Reference> venues;
    private Reference league;

    // Getters y setters
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
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
    public boolean isTimeValid() {
        return timeValid;
    }
    public void setTimeValid(boolean timeValid) {
        this.timeValid = timeValid;
    }
    public List<CompetitionDTO> getCompetitions() {
        return competitions;
    }
    public void setCompetitions(List<CompetitionDTO> competitions) {
        this.competitions = competitions;
    }
    public List<Reference> getVenues() {
        return venues;
    }
    public void setVenues(List<Reference> venues) {
        this.venues = venues;
    }
    public Reference getLeague() {
        return league;
    }
    public void setLeague(Reference league) {
        this.league = league;
    }

    // Clase para representar referencias simples (season, seasonType, league, etc.)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Reference {
        @JsonProperty("$ref")
        private String ref;
        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
    }

    // Clase para representar la competición del evento
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitionDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String date;
        private boolean boxscoreAvailable;
        private boolean lineupAvailable;
        private SourceDTO boxscoreSource;
        private SourceDTO playByPlaySource;
        private VenueDTO venue;
        private List<CompetitorDTO> competitors;
        private List<Object> notes;
        private FormatDTO format;

        // Getters y setters
        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
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
        public boolean isBoxscoreAvailable() {
            return boxscoreAvailable;
        }
        public void setBoxscoreAvailable(boolean boxscoreAvailable) {
            this.boxscoreAvailable = boxscoreAvailable;
        }
        public boolean isLineupAvailable() {
            return lineupAvailable;
        }
        public void setLineupAvailable(boolean lineupAvailable) {
            this.lineupAvailable = lineupAvailable;
        }
        public SourceDTO getBoxscoreSource() {
            return boxscoreSource;
        }
        public void setBoxscoreSource(SourceDTO boxscoreSource) {
            this.boxscoreSource = boxscoreSource;
        }
        public SourceDTO getPlayByPlaySource() {
            return playByPlaySource;
        }
        public void setPlayByPlaySource(SourceDTO playByPlaySource) {
            this.playByPlaySource = playByPlaySource;
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
        public List<Object> getNotes() {
            return notes;
        }
        public void setNotes(List<Object> notes) {
            this.notes = notes;
        }
        public FormatDTO getFormat() {
            return format;
        }
        public void setFormat(FormatDTO format) {
            this.format = format;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SourceDTO {
        private String id;
        private String description;
        private String state;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VenueDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String fullName;
        private String shortName;
        private AddressDTO address;
        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getFullName() {
            return fullName;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
        public String getShortName() {
            return shortName;
        }
        public void setShortName(String shortName) {
            this.shortName = shortName;
        }
        public AddressDTO getAddress() {
            return address;
        }
        public void setAddress(AddressDTO address) {
            this.address = address;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AddressDTO {
        private String city;
        private String country;
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitorDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String uid;
        private String type;
        private int order;
        private String homeAway;
        private boolean winner;
        private Reference teamRef;
        private Reference scoreRef;
        private Reference linescores;   
        private UniformDTO uniform;

        private ScoreValueDTO score;
        private TeamDTO team;
        @JsonProperty("roster")
        private LineUpDTO lineup;

        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getUid() {
            return uid;
        }
        public void setUid(String uid) {
            this.uid = uid;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public int getOrder() {
            return order;
        }
        public void setOrder(int order) {
            this.order = order;
        }
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
        public Reference getTeamRef() {
            return teamRef;
        }
        public void setTeamRef(Reference teamRef) {
            this.teamRef = teamRef;
        }
        public Reference getScoreRef() {
            return scoreRef;
        }
        public void setScoreRef(Reference scoreRef) {
            this.scoreRef = scoreRef;
        }
        public Reference getLinescores() {
            return linescores;
        }
        public void setLinescores(Reference linescores) {
            this.linescores = linescores;
        }
        public UniformDTO getUniform() {
            return uniform;
        }
        public void setUniform(UniformDTO uniform) {
            this.uniform = uniform;
        }
        public ScoreValueDTO getScore() {
            return score;
        }
        public void setScore(ScoreValueDTO score) {
            this.score = score;
        }
        public TeamDTO getTeam() {
            return team;
        }
        public void setTeam(TeamDTO team) {
            this.team = team;
        }
        public LineUpDTO getLineup() {
            return lineup;
        }
        public void setLineup(LineUpDTO lineup) {
            this.lineup = lineup;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UniformDTO {
        private String type;
        private String color;
        private String alternateColor;
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public String getAlternateColor() {
            return alternateColor;
        }
        public void setAlternateColor(String alternateColor) {
            this.alternateColor = alternateColor;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FormatDTO {
        private RegulationDTO regulation;
        public RegulationDTO getRegulation() {
            return regulation;
        }
        public void setRegulation(RegulationDTO regulation) {
            this.regulation = regulation;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RegulationDTO {
        private int periods;
        private String displayName;
        private String slug;
        private int clock;
        public int getPeriods() {
            return periods;
        }
        public void setPeriods(int periods) {
            this.periods = periods;
        }
        public String getDisplayName() {
            return displayName;
        }
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        public String getSlug() {
            return slug;
        }
        public void setSlug(String slug) {
            this.slug = slug;
        }
        public int getClock() {
            return clock;
        }
        public void setClock(int clock) {
            this.clock = clock;
        }
    }
}
