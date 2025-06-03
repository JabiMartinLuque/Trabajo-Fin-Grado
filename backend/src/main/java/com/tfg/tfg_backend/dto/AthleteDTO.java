package com.tfg.tfg_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AthleteDTO {

    @JsonProperty("$ref")
    private String ref;

    private String id;
    private String uid;
    private String type;
    private String guid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String displayName;
    private String shortName;
    private Integer weight;
    private String displayWeight;
    private Integer height;
    private String displayHeight;
    private Integer age;
    private String dateOfBirth;
    private String gender;
    private String citizenship;
    private String slug;
    private String jersey;
    private Boolean active;
    private Boolean profiled;
    
    // Información del país de ciudadanía
    @JsonProperty("citizenshipCountry")
    private CitizenshipCountry citizenshipCountry;
    
    // Información de la bandera
    private Flag flag;
    
    // Información de la posición
    private Position position;
    
    
    // Para campos que solo son referencias (por ejemplo, team, statistics, etc.), podemos extraer el "$ref"
    @JsonProperty("team")
    public void unpackTeam(Object teamObj) {
        if (teamObj instanceof java.util.Map) {
            Object refVal = ((java.util.Map<?, ?>) teamObj).get("$ref");
            if (refVal != null) {
                this.teamRef = refVal.toString();
            }
        }
    }
    private String teamRef; // URL o ref del equipo

    // Análogos para otros campos (opcional)
    // ...

    // Getters y setters para todos los campos
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
    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getDisplayWeight() {
        return displayWeight;
    }
    public void setDisplayWeight(String displayWeight) {
        this.displayWeight = displayWeight;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public String getDisplayHeight() {
        return displayHeight;
    }
    public void setDisplayHeight(String displayHeight) {
        this.displayHeight = displayHeight;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCitizenship() {
        return citizenship;
    }
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getJersey() {
        return jersey;
    }
    public void setJersey(String jersey) {
        this.jersey = jersey;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Boolean getProfiled() {
        return profiled;
    }
    public void setProfiled(Boolean profiled) {
        this.profiled = profiled;
    }
    public CitizenshipCountry getCitizenshipCountry() {
        return citizenshipCountry;
    }
    public void setCitizenshipCountry(CitizenshipCountry citizenshipCountry) {
        this.citizenshipCountry = citizenshipCountry;
    }
    public Flag getFlag() {
        return flag;
    }
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public String getTeamRef() {
        return teamRef;
    }
    public void setTeamRef(String teamRef) {
        this.teamRef = teamRef;
    }

    // Nested static classes para objetos anidados

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CitizenshipCountry {
        private String alternateId;
        private String abbreviation;

        public String getAlternateId() {
            return alternateId;
        }
        public void setAlternateId(String alternateId) {
            this.alternateId = alternateId;
        }
        public String getAbbreviation() {
            return abbreviation;
        }
        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flag {
        private String href;
        private String alt;
        private List<String> rel;

        public String getHref() {
            return href;
        }
        public void setHref(String href) {
            this.href = href;
        }
        public String getAlt() {
            return alt;
        }
        public void setAlt(String alt) {
            this.alt = alt;
        }
        public List<String> getRel() {
            return rel;
        }
        public void setRel(List<String> rel) {
            this.rel = rel;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Position {
        @JsonProperty("$ref")
        private String ref;
        private String id;
        private String name;
        private String displayName;
        private String abbreviation;
        private boolean leaf;

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
        public boolean isLeaf() {
            return leaf;
        }
        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }
    }

}

