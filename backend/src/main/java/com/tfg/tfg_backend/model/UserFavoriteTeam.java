package com.tfg.tfg_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "user_favorite_teams",
    uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "team_id"}) }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoriteTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Asociación al usuario (su clave primaria)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    // Aquí se guarda el identificador del equipo (por ejemplo, "83" o "esp.1" según la API)
    @Column(name = "team_id", nullable = false)
    private String teamId;
}
