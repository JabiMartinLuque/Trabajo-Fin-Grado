package com.tfg.tfg_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class LineUpDTO {
        @JsonProperty("$ref")
        private String ref;
        @JsonProperty("formation")
        private FormationDTO formation;
        @JsonProperty("entries")
        private List<EntryDTO> entries;


        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
        public FormationDTO getFormation() {
            return formation;
        }
        public void setFormation(FormationDTO formation) {
            this.formation = formation;
        }
        public List<EntryDTO> getEntries() {
            return entries;
        }
        public void setEntries(List<EntryDTO> entries) {
            this.entries = entries;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class FormationDTO {
            private String id;
            private String summary;
            private String formationClass;
            private String name;
            private Integer numRows;
    
            public String getId() {
                return id;
            }
            public void setId(String id) {
                this.id = id;
            }
            public String getSummary() {
                return summary;
            }
            public void setSummary(String summary) {
                this.summary = summary;
            }
            public String getFormationClass() {
                return formationClass;
            }
            public void setFormationClass(String formationClass) {
                this.formationClass = formationClass;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
            public Integer getNumRows() {
                return numRows;
            }
            public void setNumRows(Integer numRows) {
                this.numRows = numRows;
            }
        }
    
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EntryDTO {
            private String playerId;
            private Boolean starter;
            private String jersey;
            private String formationPlace;
    
            public String getPlayerId() {
                return playerId;
            }
            public void setPlayerId(String playerId) {
                this.playerId = playerId;
            }
            public Boolean getStarter() {
                return starter;
            }
            public void setStarter(Boolean starter) {
                this.starter = starter;
            }
            public String getJersey() {
                return jersey;
            }
            public void setJersey(String jersey) {
                this.jersey = jersey;
            }
            public String getFormationPlace() {
                return formationPlace;
            }
            public void setFormationPlace(String formationPlace) {
                this.formationPlace = formationPlace;
            }
        }
}

    
