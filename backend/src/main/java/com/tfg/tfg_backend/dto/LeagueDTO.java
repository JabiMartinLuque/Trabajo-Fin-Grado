package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDTO {

    @JsonProperty("$ref")
    private String ref;

    private String id;
    private String guid;
    private String uid;
    private String alternateId;
    private String name;
    private String displayName;
    private String abbreviation;
    private String shortName;
    private String midsizeName;
    private String slug;
    private boolean isTournament;
    
    private CountryDTO country;
    private SeasonDTO season;
    
    // Estas propiedades son referenciadas por URL en el JSON original.
    // Se pueden modelar como String o a su vez crear DTOs adicionales si lo deseas.
    
    private List<LinkDTO> links;
    private List<LogoDTO> logos;
    
    private String calendar;
    private String transactions;
    private String gender;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CountryDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String slug;
        private String name;
        private String abbreviation;
        private FlagDTO flag;
        // Referencia a los atletas; se modela como String (URL) por simplicidad.
        private String athletes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlagDTO {
        private String href;
        private String alt;
        private List<String> rel;
    }

    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkDTO {
        private String language;
        private List<String> rel;
        private String href;
        private String text;
        private String shortText;
        private boolean isExternal;
        private boolean isPremium;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogoDTO {
        private String href;
        private int width;
        private int height;
        private String alt;
        private List<String> rel;
        private String lastUpdated;
    }
}
