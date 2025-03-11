package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecordDTO {

        private String id;
        private String name;
        private String abbreviation;
        private String displayName;
        private String shortDisplayName;
        private String summary;
        private String displayValue;

        @JsonProperty("stats")
        private List<StatDTO> stats;

        // Getters y setters
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
        public String getSummary() {
            return summary;
        }
        public void setSummary(String summary) {
            this.summary = summary;
        }
        public String getDisplayValue() {
            return displayValue;
        }
        public void setDisplayValue(String displayValue) {
            this.displayValue = displayValue;
        }
        public List<StatDTO> getStats() {
            return stats;
        }
        public void setStats(List<StatDTO> stats) {
            this.stats = stats;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class StatDTO {

            private String name;
            private String displayName;
            private String shortDisplayName;
            private String description;
            private String abbreviation;
            private Double value;  // Puede ser numérico, se usa Double como ejemplo
            private String displayValue;

            // Getters y setters
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
            public String getDescription() {
                return description;
            }
            public void setDescription(String description) {
                this.description = description;
            }
            public String getAbbreviation() {
                return abbreviation;
            }
            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }
            public Double getValue() {
                return value;
            }
            public void setValue(Double value) {
                this.value = value;
            }
            public String getDisplayValue() {
                return displayValue;
            }
            public void setDisplayValue(String displayValue) {
                this.displayValue = displayValue;
            }
        }
    }
}
