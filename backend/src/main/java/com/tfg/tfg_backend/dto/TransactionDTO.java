//// filepath: c:\Users\jamar\Trabajo-Fin-Grado\backend\src\main\java\com\tfg\tfg_backend\dto\TransactionDTO.java
package com.tfg.tfg_backend.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String date;

    @JsonProperty("athlete")
    private AthleteDTO athlete;

    @JsonProperty("from")
    private TeamDTO teamFrom;

    @JsonProperty("to")
    private TeamDTO teamTo;

    private String type;
    private Double amount;
    private String displayAmount;
}