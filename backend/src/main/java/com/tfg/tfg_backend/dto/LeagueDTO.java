package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDTO {

    @JsonProperty("$ref")
    private String ref;

    private String id;
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
    
    private List<LogoDTO> logos;
    
    private String gender;

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
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public String getMidsizeName() {
        return midsizeName;
    }
    public void setMidsizeName(String midsizeName) {
        this.midsizeName = midsizeName;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public boolean isTournament() {
        return isTournament;
    }
    public void setTournament(boolean isTournament) {
        this.isTournament = isTournament;
    }
    public CountryDTO getCountry() {
        return country;
    }
    public void setCountry(CountryDTO country) {
        this.country = country;
    }
    public SeasonDTO getSeason() {
        return season;
    }
    public void setSeason(SeasonDTO season) {
        this.season = season;
    }
    public List<LogoDTO> getLogos() {
        return logos;
    }
    public void setLogos(List<LogoDTO> logos) {
        this.logos = logos;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    } 

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CountryDTO {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String slug;
        private String name;
        private String abbreviation;
        private FlagDTO flag;

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
        public FlagDTO getFlag() {
            return flag;
        }
        public void setFlag(FlagDTO flag) {
            this.flag = flag;
        }

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlagDTO {
        private String href;
        private String alt;
        private List<String> rel;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LogoDTO {
        private String href;
        private int width;
        private int height;
        private String alt;
        private List<String> rel;
        private String lastUpdated;
    }
}
