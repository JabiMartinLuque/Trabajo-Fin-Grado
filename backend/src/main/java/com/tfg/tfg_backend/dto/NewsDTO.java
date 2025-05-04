package com.tfg.tfg_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String header;
    private List<ArticleDTO> articles;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArticleDTO {
        private String type;
        private String headline;
        private String description;
        private List<ImageDTO> images;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageDTO {
        private String type;
        private Integer width;
        private Integer height;
        private String url;
    }
}
