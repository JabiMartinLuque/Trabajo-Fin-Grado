package com.tfg.tfg_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsDTO {

    private String teamRef;
    private String seasonRef;
    
    private splitsDTO splits;

    // Getters & Setters
    public String getTeamRef() {
        return teamRef;
    }
    public void setTeamRef(String teamRef) {
        this.teamRef = teamRef;
    }
    public String getSeasonRef() {
        return seasonRef;
    }
    public void setSeasonRef(String seasonRef) {
        this.seasonRef = seasonRef;
    }
    public splitsDTO getSplits() {
        return splits;
    }
    public void setSplits(splitsDTO splits) {
        this.splits = splits;
    }

    @JsonProperty("team")
    public void setTeam(Object teamObj) {
        if (teamObj instanceof java.util.Map) {
            Object ref = ((java.util.Map<?, ?>) teamObj).get("$ref");
            if (ref != null) {
                this.teamRef = ref.toString();
            }
        }
    }

    @JsonProperty("season")
    public void setSeason(Object seasonObj) {
        if (seasonObj instanceof java.util.Map) {
            Object ref = ((java.util.Map<?, ?>) seasonObj).get("$ref");
            if (ref != null) {
                this.seasonRef = ref.toString();
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class splitsDTO {

        private String id;
        private String name;
        
        @JsonProperty("categories")
        private List<CategoryDTO> categories;

        // Getters & Setters
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
        public List<CategoryDTO> getCategory() {
            return categories;
        }
        public void setCategory(List<CategoryDTO> categories) {
            this.categories = categories;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class CategoryDTO {
            
            private String name;
            private String displayName;
            private String shortDisplayName;
            private String abbreviation;
            private List<StatDTO> stats;
            
            // Getters & Setters
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
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
            public List<StatDTO> getStats() {
                return stats;
            }
            public void setStats(List<StatDTO> stats) {
                this.stats = stats;
            }
        }
    }
}
