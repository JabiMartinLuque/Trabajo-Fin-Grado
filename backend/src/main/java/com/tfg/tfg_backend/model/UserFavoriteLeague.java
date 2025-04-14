package com.tfg.tfg_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "user_favorite_leagues",
    uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "league_id"}) }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserFavoriteLeague {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // Asociación al usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Aquí se guarda el identificador de la liga que se sigue (ejemplo "esp.1")
    @Column(name = "league_id", nullable = false)
    private String leagueId;
}
