package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamEventDTO {

    @JsonProperty("$ref")
    private String ref;
    private String id;
    private String uid;
    private String date;
    private String name;
    private String shortName;
    private Reference season;
    private Reference seasonType;
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
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
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
    public Reference getSeason() {
        return season;
    }
    public void setSeason(Reference season) {
        this.season = season;
    }
    public Reference getSeasonType() {
        return seasonType;
    }
    public void setSeasonType(Reference seasonType) {
        this.seasonType = seasonType;
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
        private String guid;
        private String uid;
        private String date;
        private Integer attendance;
        private boolean necessary;
        private boolean timeValid;
        private boolean neutralSite;
        private boolean divisionCompetition;
        private boolean conferenceCompetition;
        private boolean previewAvailable;
        private boolean recapAvailable;
        private boolean boxscoreAvailable;
        private boolean lineupAvailable;
        private boolean gamecastAvailable;
        private boolean playByPlayAvailable;
        private boolean commentaryAvailable;
        private boolean pickcenterAvailable;
        private boolean summaryAvailable;
        private boolean liveAvailable;
        private boolean ticketsAvailable;
        private boolean shotChartAvailable;
        private boolean timeoutsAvailable;
        private boolean possessionArrowAvailable;
        private boolean onWatchESPN;
        private boolean recent;
        private boolean bracketAvailable;
        private boolean wallclockAvailable;
        private SourceDTO boxscoreSource;
        private SourceDTO playByPlaySource;
        private VenueDTO venue;
        private List<CompetitorDTO> competitors;
        private List<Object> notes;
        private Reference situation;
        private Reference status;
        private Reference odds;
        private Reference broadcasts;
        private Reference officials;
        private Reference details;
        private Reference commentaries;
        private Reference groups;
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
        public String getGuid() {
            return guid;
        }
        public void setGuid(String guid) {
            this.guid = guid;
        }
        public String getUid() {
            return uid;
        }
        public void setUid(String uid) {
            this.uid = uid;
        }
        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }
        public Integer getAttendance() {
            return attendance;
        }
        public void setAttendance(Integer attendance) {
            this.attendance = attendance;
        }
        public boolean isNecessary() {
            return necessary;
        }
        public void setNecessary(boolean necessary) {
            this.necessary = necessary;
        }
        public boolean isTimeValid() {
            return timeValid;
        }
        public void setTimeValid(boolean timeValid) {
            this.timeValid = timeValid;
        }
        public boolean isNeutralSite() {
            return neutralSite;
        }
        public void setNeutralSite(boolean neutralSite) {
            this.neutralSite = neutralSite;
        }
        public boolean isDivisionCompetition() {
            return divisionCompetition;
        }
        public void setDivisionCompetition(boolean divisionCompetition) {
            this.divisionCompetition = divisionCompetition;
        }
        public boolean isConferenceCompetition() {
            return conferenceCompetition;
        }
        public void setConferenceCompetition(boolean conferenceCompetition) {
            this.conferenceCompetition = conferenceCompetition;
        }
        public boolean isPreviewAvailable() {
            return previewAvailable;
        }
        public void setPreviewAvailable(boolean previewAvailable) {
            this.previewAvailable = previewAvailable;
        }
        public boolean isRecapAvailable() {
            return recapAvailable;
        }
        public void setRecapAvailable(boolean recapAvailable) {
            this.recapAvailable = recapAvailable;
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
        public boolean isGamecastAvailable() {
            return gamecastAvailable;
        }
        public void setGamecastAvailable(boolean gamecastAvailable) {
            this.gamecastAvailable = gamecastAvailable;
        }
        public boolean isPlayByPlayAvailable() {
            return playByPlayAvailable;
        }
        public void setPlayByPlayAvailable(boolean playByPlayAvailable) {
            this.playByPlayAvailable = playByPlayAvailable;
        }
        public boolean isCommentaryAvailable() {
            return commentaryAvailable;
        }
        public void setCommentaryAvailable(boolean commentaryAvailable) {
            this.commentaryAvailable = commentaryAvailable;
        }
        public boolean isPickcenterAvailable() {
            return pickcenterAvailable;
        }
        public void setPickcenterAvailable(boolean pickcenterAvailable) {
            this.pickcenterAvailable = pickcenterAvailable;
        }
        public boolean isSummaryAvailable() {
            return summaryAvailable;
        }
        public void setSummaryAvailable(boolean summaryAvailable) {
            this.summaryAvailable = summaryAvailable;
        }
        public boolean isLiveAvailable() {
            return liveAvailable;
        }
        public void setLiveAvailable(boolean liveAvailable) {
            this.liveAvailable = liveAvailable;
        }
        public boolean isTicketsAvailable() {
            return ticketsAvailable;
        }
        public void setTicketsAvailable(boolean ticketsAvailable) {
            this.ticketsAvailable = ticketsAvailable;
        }
        public boolean isShotChartAvailable() {
            return shotChartAvailable;
        }
        public void setShotChartAvailable(boolean shotChartAvailable) {
            this.shotChartAvailable = shotChartAvailable;
        }
        public boolean isTimeoutsAvailable() {
            return timeoutsAvailable;
        }
        public void setTimeoutsAvailable(boolean timeoutsAvailable) {
            this.timeoutsAvailable = timeoutsAvailable;
        }
        public boolean isPossessionArrowAvailable() {
            return possessionArrowAvailable;
        }
        public void setPossessionArrowAvailable(boolean possessionArrowAvailable) {
            this.possessionArrowAvailable = possessionArrowAvailable;
        }
        public boolean isOnWatchESPN() {
            return onWatchESPN;
        }
        public void setOnWatchESPN(boolean onWatchESPN) {
            this.onWatchESPN = onWatchESPN;
        }
        public boolean isRecent() {
            return recent;
        }
        public void setRecent(boolean recent) {
            this.recent = recent;
        }
        public boolean isBracketAvailable() {
            return bracketAvailable;
        }
        public void setBracketAvailable(boolean bracketAvailable) {
            this.bracketAvailable = bracketAvailable;
        }
        public boolean isWallclockAvailable() {
            return wallclockAvailable;
        }
        public void setWallclockAvailable(boolean wallclockAvailable) {
            this.wallclockAvailable = wallclockAvailable;
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
        public Reference getSituation() {
            return situation;
        }
        public void setSituation(Reference situation) {
            this.situation = situation;
        }
        public Reference getStatus() {
            return status;
        }
        public void setStatus(Reference status) {
            this.status = status;
        }
        public Reference getOdds() {
            return odds;
        }
        public void setOdds(Reference odds) {
            this.odds = odds;
        }
        public Reference getBroadcasts() {
            return broadcasts;
        }
        public void setBroadcasts(Reference broadcasts) {
            this.broadcasts = broadcasts;
        }
        public Reference getOfficials() {
            return officials;
        }
        public void setOfficials(Reference officials) {
            this.officials = officials;
        }
        public Reference getDetails() {
            return details;
        }
        public void setDetails(Reference details) {
            this.details = details;
        }
        public Reference getCommentaries() {
            return commentaries;
        }
        public void setCommentaries(Reference commentaries) {
            this.commentaries = commentaries;
        }
        public Reference getGroups() {
            return groups;
        }
        public void setGroups(Reference groups) {
            this.groups = groups;
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
        private List<Object> images;
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
        public List<Object> getImages() {
            return images;
        }
        public void setImages(List<Object> images) {
            this.images = images;
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
        private Reference team;
        private Reference score;
        private Reference linescores;
        private Reference roster;
        private Reference statistics;
        private Reference leaders;
        private Reference record;
        private UniformDTO uniform;
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
        public Reference getTeam() {
            return team;
        }
        public void setTeam(Reference team) {
            this.team = team;
        }
        public Reference getScore() {
            return score;
        }
        public void setScore(Reference score) {
            this.score = score;
        }
        public Reference getLinescores() {
            return linescores;
        }
        public void setLinescores(Reference linescores) {
            this.linescores = linescores;
        }
        public Reference getRoster() {
            return roster;
        }
        public void setRoster(Reference roster) {
            this.roster = roster;
        }
        public Reference getStatistics() {
            return statistics;
        }
        public void setStatistics(Reference statistics) {
            this.statistics = statistics;
        }
        public Reference getLeaders() {
            return leaders;
        }
        public void setLeaders(Reference leaders) {
            this.leaders = leaders;
        }
        public Reference getRecord() {
            return record;
        }
        public void setRecord(Reference record) {
            this.record = record;
        }
        public UniformDTO getUniform() {
            return uniform;
        }
        public void setUniform(UniformDTO uniform) {
            this.uniform = uniform;
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
