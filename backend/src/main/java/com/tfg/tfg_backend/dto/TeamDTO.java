package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDTO {

    // Campos directos de la respuesta del equipo
    @JsonProperty("$ref")
    private String ref;
    
    private String id;
    private String slug;
    private String location;
    private String name;
    private String nickname;
    private String abbreviation;
    private String displayName;
    private String shortDisplayName;
    private String color;
    private String alternateColor;
    
    // Campo para el logo: se extrae del array "logos" (el primer elemento)
    private String logo;
    
    // Referencias a otros recursos (se almacenan como String con el $ref)
    @JsonProperty("record")
    private String recordRef;
    
    @JsonProperty("athletes")
    private String athletesRef;
    
    private GroupDTO groups;

    @JsonProperty("statistics")
    private String statisticsRef;
    private String eventsRef;  
    private String form; 
    
    @JsonProperty("defaultLeague")
    private String defaultLeagueRef;
    
    // Campo anidado para la información del venue
    private VenueDTO venue;

    private List<String> competitions;

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
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getRecordRef() {
        return recordRef;
    }
    public void setRecordRef(String recordRef) {
        this.recordRef = recordRef;
    }
    public String getAthletesRef() {
        return athletesRef;
    }
    public void setAthletesRef(String athletesRef) {
        this.athletesRef = athletesRef;
    }
    public GroupDTO getGroup() {
        return groups;
    }
    public void setGroup(GroupDTO groups) {
        this.groups = groups;
    }
    public String getStatisticsRef() {
        return statisticsRef;
    }
    public void setStatisticsRef(String statisticsRef) {
        this.statisticsRef = statisticsRef;
    }
    public String getEventsRef() {
        return eventsRef;
    }
    public void setEventsRef(String eventsRef) {
        this.eventsRef = eventsRef;
    }
    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getDefaultLeagueRef() {
        return defaultLeagueRef;
    }
    public void setDefaultLeagueRef(String defaultLeagueRef) {
        this.defaultLeagueRef = defaultLeagueRef;
    }
    public VenueDTO getVenue() {
        return venue;
    }
    public void setVenue(VenueDTO venue) {
        this.venue = venue;
    }

    public List<String> getCompetitions() {
        return competitions;
    }
    public void setCompetitions(List<String> leagueIds) {
        this.competitions = leagueIds;
    }
    public String getLeague() {
        if (defaultLeagueRef != null && !defaultLeagueRef.isEmpty()) {
            String[] parts = defaultLeagueRef.split("/");
            for (int i = 0; i < parts.length; i++) {
                if ("leagues".equals(parts[i]) && i + 1 < parts.length) {
                    String leaguePart = parts[i + 1];
                    // Elimina la query si existe
                    if (leaguePart.contains("?")) {
                        leaguePart = leaguePart.split("\\?")[0];
                    }
                    return leaguePart;
                }
            }
        }
        return null;
    }
    
    public String getCurrentSeason() {
        if (recordRef != null && !recordRef.isEmpty()) {
            // Dividimos la URL por "/"
            String[] parts = recordRef.split("/");
            for (int i = 0; i < parts.length; i++) {
                if ("seasons".equals(parts[i]) && i + 1 < parts.length) {
                    String seasonPart = parts[i + 1];
                    // Si el segmento contiene parámetros, se eliminan
                    if (seasonPart.contains("?")) {
                        seasonPart = seasonPart.split("\\?")[0];
                    }
                    return seasonPart;
                }
            }
        }
        // Valor por defecto si no se encuentra la temporada
        return "2024";
    }

    // Setter personalizado para "logos": extrae el primer logo del array y guarda su href
    @JsonProperty("logos")
    public void setLogosFromArray(List<Object> logos) {
        if (logos != null && !logos.isEmpty()) {
            Object firstElement = logos.get(0);
            if (firstElement instanceof java.util.Map) {
                java.util.Map<?, ?> logoMap = (java.util.Map<?, ?>) firstElement;
                Object href = logoMap.get("href");
                if (href != null) {
                    this.logo = href.toString();
                }
            }
        }
    }

    // Setter para "record"
    @JsonProperty("record")
    public void setRecordFromObject(Object recordObj) {
        if (recordObj instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) recordObj;
            Object ref = map.get("$ref");
            if (ref != null) {
                this.recordRef = ref.toString();
            }
        }
    }

    // Setter para "athletes"
    @JsonProperty("athletes")
    public void setAthletesFromObject(Object athletesObj) {
        if (athletesObj instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) athletesObj;
            Object ref = map.get("$ref");
            if (ref != null) {
                this.athletesRef = ref.toString();
            }
        }
    }

    // Puedes agregar setters similares para otros campos que son referencias
    // Por ejemplo: groups, statistics, leaders, injuries, notes, franchise, events, coaches, seasons, summary, defaultLeague

    @JsonProperty("statistics")
    public void setStatisticsFromObject(Object statisticsObj) {
        if (statisticsObj instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) statisticsObj;
            Object ref = map.get("$ref");
            if (ref != null) {
                this.statisticsRef = ref.toString();
            }
        }
    }

    @JsonProperty("events")
    public void setEventsFromObject(Object eventsObj) {
        if (eventsObj instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) eventsObj;
            Object ref = map.get("$ref");
            if (ref != null) {
                this.eventsRef = ref.toString();
            }
        }
    }

    @JsonProperty("defaultLeague")
    public void setDefaultLeagueFromObject(Object defaultLeagueObj) {
        if (defaultLeagueObj instanceof java.util.Map) {
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) defaultLeagueObj;
            Object ref = map.get("$ref");
            if (ref != null) {
                this.defaultLeagueRef = ref.toString();
            }
        }
    }
    
    // Clases anidadas para datos de "alternateIds", "venue" y "links"

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AlternateIds {
        @JsonProperty("sdr")
        private String sdr;

        public String getSdr() {
            return sdr;
        }
        public void setSdr(String sdr) {
            this.sdr = sdr;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VenueDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String fullName;
        private String shortName;
        private Address address;

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
        public Address getAddress() {
            return address;
        }
        public void setAddress(Address address) {
            this.address = address;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
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
    public static class GroupDTO {

        private String name;
        
        private SeasonDTO season;
        
        @JsonProperty("standing")
        private String standingRef;

        // Getters y setters
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }

        public SeasonDTO getSeason() {
            return season;
        }

        public void setSeason(SeasonDTO season) {
            this.season = season;
        }

        public String getStandingRef() {
            return standingRef;
        }

        public void setStandingRef(String standingRef) {
            this.standingRef = standingRef;
        }

        public String getLeague() {
            String[] parts = standingRef.split("=");
            return parts[1];
        }
    }
}
