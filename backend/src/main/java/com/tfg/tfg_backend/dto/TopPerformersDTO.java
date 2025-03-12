package com.tfg.tfg_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPerformersDTO {
    private String id;
    private String name;
    private String abbrevation;
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
    public String getAbbrevation() {
        return abbrevation;
    }
    public void setAbbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
    }
    public List<CategoryDTO> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public static class CategoryDTO {
        private String name;
        private String displayName;
        private String shortDisplayName;
        private String abbreviation;
        private List<LeaderDTO> leaders;

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
        public List<LeaderDTO> getLeaders() {
            return leaders;
        }
        public void setLeaders(List<LeaderDTO> leaders) {
            this.leaders = leaders;
        }

        public static class LeaderDTO{
            private String displayValue;
            private String shortDisplayValue;
            private String value;

            private String athelteRef;

            private String teamRef;

            private String statisticsRef;

            // Getters & Setters
            public String getDisplayValue() {   
                return displayValue;
            }
            public void setDisplayValue(String displayValue) {
                this.displayValue = displayValue;
            }
            public String getShortDisplayValue() {
                return shortDisplayValue;
            }
            public void setShortDisplayValue(String shortDisplayValue) {
                this.shortDisplayValue = shortDisplayValue;
            }
            public String getValue() {
                return value;
            }
            public void setValue(String value) {
                this.value = value;
            }

            public String getAthelteRef() {
                return athelteRef;
            }
            public void setAthelteRef(String athelteRef) {
                this.athelteRef = athelteRef;
            }
            public String getTeamRef() {
                return teamRef;
            }
            public void setTeamRef(String teamRef) {
                this.teamRef = teamRef;
            }
            public String getStatisticsRef() {
                return statisticsRef;
            }
            public void setStatisticsRef(String statisticsRef) {
                this.statisticsRef = statisticsRef;
            }

            @JsonProperty("athlete")
            public void setAthlete(Object athleteObj) {
                if (athleteObj instanceof java.util.Map) {
                    Object ref = ((java.util.Map<?, ?>) athleteObj).get("$ref");
                    if (ref != null) {
                        this.athelteRef = ref.toString();
                    }
                }
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

            @JsonProperty("statistics")
            public void setStatistic(Object statisticObj) {
                if (statisticObj instanceof java.util.Map) {
                    Object ref = ((java.util.Map<?, ?>) statisticObj).get("$ref");
                    if (ref != null) {
                        this.statisticsRef = ref.toString();
                    }
                }
            }

        }
    }
}
