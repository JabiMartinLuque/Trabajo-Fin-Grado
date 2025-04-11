package com.tfg.tfg_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "user_favorite_players",
    uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "player_id"}) }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserFavoritePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Asociación al usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    // Aquí se guarda el identificador del jugador que se sigue (ejemplo "231388")
    @Column(name = "player_id", nullable = false)
    private String playerId;
}
