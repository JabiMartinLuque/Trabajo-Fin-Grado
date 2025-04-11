    package com.tfg.tfg_backend.model;

    import jakarta.persistence.*;
    import lombok.*;
    import java.util.List;
    
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    @Entity
    @Table(name = "users")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class User {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        private String email;

        @Enumerated(EnumType.STRING)
        private Role role; // Rol del usuario (ADMIN, USER)

        // Relación con jugadores favoritos
        @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JsonIgnoreProperties("user")
        private List<UserFavoritePlayer> favoritePlayers;

        // Relación con equipos favoritos
        @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JsonIgnoreProperties("user")
        private List<UserFavoriteTeam> favoriteTeams;

    }
